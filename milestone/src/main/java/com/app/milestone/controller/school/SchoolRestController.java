package com.app.milestone.controller.school;

import com.app.milestone.domain.*;
import com.app.milestone.entity.People;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schoolrest/*")
@Slf4j
public class SchoolRestController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final ReplyService replyService;

    //    보육원 목록
    @GetMapping(value = {"/list/{page}", "/list/{page}/{schoolAddress}", "/list/{page}/{schoolAddress}/{schoolName}"})
    public SchoolResp search(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 10);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolList(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
        return schoolResp;
    }

    //    ==========================보육원 상세=======================
    //    보육원 정보
    @GetMapping(value = {"/info/{userId}"})
    public SchoolDTO info(@PathVariable("userId") Long userId) {
        return schoolService.schoolInfo(userId);
    }

    //    보육원 기부 랭킹
    @GetMapping(value = {"/recent/{userId}"})
    public List<MoneyDTO> recent(@PathVariable("userId") Long userId) {
        return schoolService.recentDonationList(userId);
    }

    //    보육원 기부 랭킹
    @GetMapping(value = {"/ranking/{userId}"})
    public List<MoneyDTO> ranking(@PathVariable("userId") Long userId) {
        return schoolService.moneyDonationRankingForOneSchool(userId);
    }

    //    보육원 댓글
    @GetMapping(value = {"/reply/{page}/{userId}"})
    public Page<ReplyDTO> reply(@PathVariable("page") Integer page, @PathVariable("userId") Long userId) {
        return replyService.showAll(page, userId);
    }

    //    보육원 댓글 삭제
    @GetMapping(value = {"/{replyId}"})
    public void remove(@PathVariable("replyId") Long replyId) {
        replyService.remove(replyId);
    }

    //    보육원 댓글 수정
    @PostMapping("/modify")
    public void modify(@RequestBody ReplyDTO replyDTO) {
        replyService.modify(replyDTO);
    }

    //    보육원 댓글 등록
    @PostMapping("/register")
    public void register(@RequestBody ReplyDTO replyDTO) {
        replyService.register(replyDTO);
    }

    //  ==============좋아요=============
    //    내가 누른 좋아요
    @GetMapping(value = {"/likeSchool"})
    public List<Long> likeSchool() {
        Long sessionId = 132L;
        return peopleService.likeSchoolList(sessionId);
    }

    //    좋아요 개수
    @GetMapping(value = {"/likeCount/{userId}"})
    public Long likeCount(@PathVariable("userId") Long userId) {
        return peopleService.likeCount(userId);
    }

    //    좋아요 누름
    @GetMapping(value = {"/like/{userId}"})
    public void like(@PathVariable("userId") Long userId) {
        Long sessionId = 132L;
        peopleService.likeSchool(userId, sessionId);
    }

    //    좋아요 취소
    @GetMapping(value = {"/cancel/{userId}"})
    public void cancel(@PathVariable("userId") Long userId) {
        Long sessionId = 132L;
        peopleService.likeSchool(userId, sessionId);
    }


}
