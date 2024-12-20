package org.sanaa.setnence.citronix.youquiz.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String text;
    @NotNull
    private boolean isCorrect;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<QuestionAnswer>  questionAnswers;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<AnswerValidation> answerValidation;
}
