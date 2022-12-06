package com.app.milestone.service;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.Ranking;
import com.app.milestone.entity.Like;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.repository.LikeRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final LikeRepository likeRepository;

    public ResponseEntity peopleSignUp(PeopleDTO peopleDTO) {

        Optional<People> people = peopleRepository.findByUserEmail(peopleDTO.getUserEmail());

        if (people.isEmpty()) {
            People newPeople = People.builder()
                    .userEmail(peopleDTO.getUserEmail())
                    .userPassword(peopleDTO.getUserPassword())
                    .userName(peopleDTO.getUserName())
                    .peopleNickname(peopleDTO.getPeopleNickname())
                    .userPhoneNumber(peopleDTO.getUserPhoneNumber())
                    .build();

            peopleRepository.save(newPeople);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }

    //    개인회원 한 명의 정보
    public PeopleDTO onesInfo(Long userId) {
        return peopleRepository.findInfoById(userId);
    }

    //    좋아요 누름
    public void likeSchool(List<Long> userId) {
        School school = schoolRepository.findById(userId.get(0)).get();
        People people = peopleRepository.findById(userId.get(1)).get();
        Like like = new Like(school, people);
        likeRepository.save(like);
    }

    //    ===================================기부 랭킹====================================
    //    기부금 랭킹
    public List<Ranking> donationMoneyRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = peopleRepository.sortByMoneyCash();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String userName = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getUserName();
            ranking.setUserName(userName);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }

    //    방문횟수 랭킹
    public List<Ranking> donationVisitRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = peopleRepository.sortByVisitRank();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String userName = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getUserName();
            ranking.setUserName(userName);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }

    //    재능기부 횟수 랭킹
    public List<Ranking> donationTalentRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = peopleRepository.sortBytalentRank();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String userName = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getUserName();
            ranking.setUserName(userName);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }




    /* 관리자페이지 */
    //전체회원중 일반회원 불러오기
    public List<PeopleDTO> peopleOnly(){
        return peopleRepository.findByPeopleOnly();
    }

    //일반회원 중 가입날짜 오름차순 정렬
    public List<PeopleDTO> peopleOnlyAsc(){
        return peopleRepository.findByPeopleOnlyAsc();
    }
}
