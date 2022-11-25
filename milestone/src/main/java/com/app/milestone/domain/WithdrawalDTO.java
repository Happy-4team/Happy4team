package com.app.milestone.domain;

import com.app.milestone.entity.User;
import com.app.milestone.entity.Withdrawal;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class WithdrawalDTO {
    private Long withdrawalId;
    private String withdrawalReason;

    public Withdrawal toEntity() {
        return Withdrawal.builder()
                .withdrawalReason(withdrawalReason)
                .build();
    }

    @QueryProjection
    public WithdrawalDTO(Long withdrawalId, String withdrawalReason) {
        this.withdrawalId = withdrawalId;
        this.withdrawalReason = withdrawalReason;
    }
}