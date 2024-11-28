package org.sanaa.setnence.citronix.youquiz.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest extends UserRequestDTO {
    private LocalDate enrollmentDate;
}
