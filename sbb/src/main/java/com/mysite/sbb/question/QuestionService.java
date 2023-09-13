package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
    
    public Question getQuestion(Integer id) {
    	Optional<Question> question = this.questionRepository.findById(id); //id값으로 Question 데이터를 조회 
    	if(question.isPresent()) {  //Question 객체는 Optional 객체이기 때문에 isPresent로 해당 데이터가 존재하는지 검색 
    		return question.get();
    	} else {
    		throw new DataNotFoundException("question not found");
    	}
    }
    
    //제목과 내용을 입력으로 하여 질문 데이터를 저장하는 메소드. 
    public void create(String subject, String content) {
    	Question q= new Question();
    	q.setSubject(subject);
    	q.setContent(content);
    	q.setCreateDate(LocalDateTime.now());
    	this.questionRepository.save(q);
    }
        

}
