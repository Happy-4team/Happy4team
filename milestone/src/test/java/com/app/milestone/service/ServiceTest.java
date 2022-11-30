package com.app.milestone.service;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.ServiceDTO;
import com.app.milestone.entity.QDonation;
import com.app.milestone.entity.School;
import com.app.milestone.entity.Service;
import com.app.milestone.entity.User;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.ServiceRepository;
import com.app.milestone.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
@Rollback(false)
public class ServiceTest {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //    보육원에 서비스 신청하고, 기부 횟수 올리기
    @Test
    public void saveTest() {
        SchoolDTO schoolDTO = new SchoolDTO();

        School school = schoolRepository.findById(11L).get();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceVisitDate(LocalDateTime.of(2023, 1, 1, 9, 30));

        Service service = serviceDTO.toEntity();
        serviceRepository.save(service);
        service.changeSchool(school);

        List<Long> updateDonationCount = jpaQueryFactory
                .select(QDonation.donation.donationId.count())
                .from(QDonation.donation)
                .where(QDonation.donation.school.userId.eq(11L))
                .fetch();

        List<Integer> intUpdateDonationCount = updateDonationCount.stream().mapToInt(Long::intValue).boxed().collect(Collectors.toList());

        User user = userRepository.findById(11L).get();

        user.update(intUpdateDonationCount.get(0));
    }
//    유저 테이블이 업데이트 할거야
//    유저 테이블에
//    n번 유저 도네이션 카운트 업데이트 할거에요
//    도네이션 아이디로 카운트 수 만큼 업데이트 함

//    UPDATE TBL_USER
//    SET DONATION_COUNT = (SELECT COUNT(DONATION_ID) FROM TBL_DONATION WHERE SCHOOL_USER_ID = 11)
//    WHERE USER_ID = 11;


//    @Test
//    public void findTest() {
//        assertThat(serviceRepository.findById(8L).get().getDonationId()).isEqualTo(8L);
//    }

//    @Test
//    public void deleteTest() {
//        serviceRepository.deleteById(8L);
//    }
}
