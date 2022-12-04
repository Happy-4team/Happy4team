package com.app.milestone.repository;

import com.app.milestone.domain.QSchoolDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.QSchool;
import com.app.milestone.entity.QUser;
import com.app.milestone.entity.School;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QSchool.school;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SchoolCustomRepositoryImpl implements SchoolCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //  메인 도움이 필요해요(쿼리프로젝션 버전)
    @Override
    public List<SchoolDTO> findAllByDonationCount() {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.schoolName,
                school.address.schoolAddress,
                school.address.schoolAddressDetail,
                school.address.schoolZipcode,
                school.schoolTeachers,
                school.schoolKids,
                school.schoolBudget,
                school.schoolBank,
                school.schoolAccount,
                school.schoolPhoneNumber,
                school.schoolQR,
                school.introduce.schoolTitle,
                school.introduce.schoolContent,
                school.userEmail,
                school.userName,
                school.userPassword,
                school.userPhoneNumber,
                school.donationCount

        )).from(school)
                .orderBy(school.donationCount.asc())
                .offset(0)
                .limit(5)
                .fetch();
    }

    //    보육원 목록(최신순)(쿼리프로젝션 버전)
    @Override
    public List<SchoolDTO> findAllByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.schoolName,
                school.address.schoolAddress,
                school.address.schoolAddressDetail,
                school.address.schoolZipcode,
                school.schoolTeachers,
                school.schoolKids,
                school.schoolBudget,
                school.schoolBank,
                school.schoolAccount,
                school.schoolPhoneNumber,
                school.schoolQR,
                school.introduce.schoolTitle,
                school.introduce.schoolContent,
                school.userEmail,
                school.userName,
                school.userPassword,
                school.userPhoneNumber,
                school.donationCount
        ))
                .where(
//                        보육원 이름 검색
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining0(search.getSchoolAddress())

                ).from(school)
                .orderBy(school.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //    보육원 목록(최신순)(쿼리프로젝션 버전)
    @Override
    public Long countByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(school.count())
                .from(school)
                .where(
//                        보육원 이름 검색
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining0(search.getSchoolAddress())
                )
                .orderBy(school.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();
    }

    //    이름검색
    private BooleanExpression schoolNameContaining(String schoolName) {
        return StringUtils.hasText(schoolName) ? school.schoolName.contains(schoolName) : null;
    }

    //    지역검색
    private BooleanBuilder schoolAddressContaining0(List<String> schoolAddresses) {
        if (schoolAddresses.get(0) == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String schoolAddress : schoolAddresses) {
            if (schoolAddress.equals("서울")) {
                booleanBuilder.or(school.address.schoolAddress.like("서울%"));
            }
            if (schoolAddress.equals("인천")) {
                booleanBuilder.or(school.address.schoolAddress.like("인천%"));
            }
            if (schoolAddress.equals("경기도")) {
                booleanBuilder.or(school.address.schoolAddress.like("경기%"));
            }
            if (schoolAddress.equals("강원도")) {
                booleanBuilder.or(school.address.schoolAddress.like("강원%"));
            }
            if (schoolAddress.equals("충청도")) {
                booleanBuilder.or(school.address.schoolAddress.like("충북%").or(school.address.schoolAddress.like("충남%")).or(school.address.schoolAddress.like("세종%")).or(school.address.schoolAddress.like("대전%")));
            }
            if (schoolAddress.equals("전라도")) {
                booleanBuilder.or(school.address.schoolAddress.like("전북%").or(school.address.schoolAddress.like("전남%")).or(school.address.schoolAddress.like("광주%")));
            }
            if (schoolAddress.equals("경상도")) {
                booleanBuilder.or(school.address.schoolAddress.like("경북%").or(school.address.schoolAddress.like("경남%")).or(school.address.schoolAddress.like("부산%")).or(school.address.schoolAddress.like("울산%")).or(school.address.schoolAddress.like("대구%")));
            }
            if (schoolAddress.equals("제주도")) {
                booleanBuilder.or(school.address.schoolAddress.like("제주%"));
            }
        }
        return booleanBuilder;
    }






    //========================관리자페이지===========================
    //보육원 목록
    @Override
    public List<School> findByCreatedDate(Pageable pageable) {
        return jpaQueryFactory.selectFrom(school)
                .orderBy(school.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //전체 회원중 보육원 회원만
    @Override
//    public List<Tuple> findBySchoolOnly (){
//        List<Tuple> tuples = new ArrayList<>();
//        tuples = jpaQueryFactory.select(new QSchoolDTO(
//                school.address.schoolAddress,
//                school.address.schoolAddressDetail,
//                school.address.schoolZipcode,
//                school.schoolBank,
//                school.schoolTeachers,
//                school.schoolKids,
//                school.schoolBudget,
//                school.userName,
//                school.schoolAccount,
//                school.schoolPhoneNumber,
//                school.schoolQR,
//                school.introduce.schoolTitle,
//                school.introduce.schoolContent,
//                school.userEmail,
//                school.schoolName,
//                school.userPassword,
//                school.userPhoneNumber,
//                school.donationCount
//                ),school.createdDate)
//                .from(school, QUser.user)
//                .where(school.userId.eq(QUser.user.userId))
//                .fetch();
//
//        return tuples;
//    };
    public List<SchoolDTO> findBySchoolOnly (){
        return jpaQueryFactory.select(new QSchoolDTO(
                school.schoolName,
                school.address.schoolAddress,
                school.address.schoolAddressDetail,
                school.address.schoolZipcode,
                school.schoolTeachers,
                school.schoolKids,
                school.schoolBudget,
                school.schoolBank,
                school.schoolAccount,
                school.schoolQR,
                school.introduce.schoolTitle,
                school.introduce.schoolContent,
                school.userEmail,
                school.userEmail,
                school.userName,
                school.userPassword,
                school.userPhoneNumber,
                school.donationCount
                ))
                .from(school, QUser.user)
                .where(school.userId.eq(QUser.user.userId))
                .fetch();
    };


    //=============================================================
}
