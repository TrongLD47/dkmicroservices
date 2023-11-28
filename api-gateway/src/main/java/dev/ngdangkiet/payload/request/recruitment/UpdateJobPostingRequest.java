package dev.ngdangkiet.payload.request.recruitment;

import dev.ngdangkiet.validation.ValidationJobPostingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ngdangkiet
 * @since 11/28/2023
 */

@Getter
@Setter
public class UpdateJobPostingRequest extends UpsertJobPostingRequest {

    @NotNull(message = "Id shouldn't be not null!")
    private Long id;

    @ValidationJobPostingStatus
    private String status;
}
