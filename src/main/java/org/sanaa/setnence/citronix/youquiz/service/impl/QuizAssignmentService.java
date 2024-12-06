package org.sanaa.setnence.citronix.youquiz.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.sanaa.setnence.citronix.youquiz.model.dto.request.QuizAssignmentRequestDTO;
import org.sanaa.setnence.citronix.youquiz.model.dto.response.QuizAssignmentResponseDTO;
import org.sanaa.setnence.citronix.youquiz.model.entity.Quiz;
import org.sanaa.setnence.citronix.youquiz.model.entity.QuizAssignment;
import org.sanaa.setnence.citronix.youquiz.model.entity.Student;
import org.sanaa.setnence.citronix.youquiz.model.mapper.QuizAssignmentMapper;
import org.sanaa.setnence.citronix.youquiz.repository.QuizAssignmentRepository;
import org.sanaa.setnence.citronix.youquiz.service.interfaces.QuizAssignmentServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAssignmentService implements QuizAssignmentServiceI {

    private final QuizAssignmentRepository quizAssignmentRepository ;
    private final QuizAssignmentMapper quizAssignmentMapper;
    private final StudentService studentService;
    private final QuizService quizService;

    public QuizAssignmentService(QuizAssignmentRepository quizAssignmentRepository, QuizAssignmentMapper quizAssignmentMapper, StudentService studentService, QuizService quizService) {
        this.quizAssignmentRepository = quizAssignmentRepository;
        this.quizAssignmentMapper = quizAssignmentMapper;
        this.studentService = studentService;
        this.quizService = quizService;
    }


    @Override
    public QuizAssignmentResponseDTO create(QuizAssignmentRequestDTO quizAssignmentRequestDTO) {
        Quiz quiz = quizService.findEntityById(quizAssignmentRequestDTO.getQuizId());
     Student student = studentService.findEntityById(quizAssignmentRequestDTO.getStudentId());
     QuizAssignment quizAssignment = quizAssignmentMapper.toEntity(quizAssignmentRequestDTO);
        quizAssignment.setStudent(student);
        quizAssignment.setQuiz(quiz);
        QuizAssignment savedAssignment = quizAssignmentRepository.save(quizAssignment);
        return quizAssignmentMapper.toResponseDTO(savedAssignment);
    }

    @Override
    public QuizAssignmentResponseDTO update(Long id, QuizAssignmentRequestDTO quizAssignmentRequestDTO) {
        QuizAssignment quizAssignment  = quizAssignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with ID: " + id));
        quizAssignmentMapper.updateEntityFromRequest(quizAssignmentRequestDTO , quizAssignment);
        QuizAssignment updatedQuizAssignment = quizAssignmentRepository.save(quizAssignment);
        return quizAssignmentMapper.toResponseDTO(updatedQuizAssignment);
    }

    @Override
    public Optional<QuizAssignmentResponseDTO> findById(Long id) {
        return quizAssignmentRepository.findById(id)
                .map(quizAssignmentMapper::toResponseDTO);
    }

    @Override
    public List<QuizAssignmentResponseDTO> findAll() {
        return quizAssignmentRepository.findAll()
                .stream()
                .map(quizAssignmentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if(!quizAssignmentRepository.existsById(id)){
            throw new EntityNotFoundException("quizAssignment not found with id " +id);
        }
     quizAssignmentRepository.deleteById(id);
    }
}
