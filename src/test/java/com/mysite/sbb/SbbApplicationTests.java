package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    @Test
    void testJpa() {        
		/*
		 * Question q1 = new Question(); q1.setSubject("sbb가 무엇인가요?");
		 * q1.setContent("sbb에 대해서 알고 싶습니다."); q1.setCreateDate(LocalDateTime.now());
		 * this.questionRepository.save(q1); // 첫번째 질문 저장
		 * 
		 * Question q2 = new Question(); q2.setSubject("스프링부트 모델 질문입니다.");
		 * q2.setContent("id는 자동으로 생성되나요?"); q2.setCreateDate(LocalDateTime.now());
		 * this.questionRepository.save(q2); // 두번째 질문 저장
		 */
    
		/*
		 * List<Question> all = this.questionRepository.findAll(); assertEquals(4,
		 * all.size());
		 * 
		 * Question q = all.get(0); assertEquals("sbb가 무엇인가요?", q.getSubject());
		 */
    	
		/*
		 * Optional<Question> oq = this.questionRepository.findById(1);
		 * if(oq.isPresent()) { Question q = oq.get(); assertEquals("sbb가 무엇인가요?",
		 * q.getSubject()); }
		 */
         
		/*
		 * Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		 * assertEquals(1, q.getId());
		 */
         
		/*
		 * Question q = this.questionRepository.findBySubjectAndContent( "sbb가 무엇인가요?",
		 * "sbb에 대해서 알고 싶습니다."); assertEquals(1, q.getId());
		 */
         
		/*
		 * List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		 * Question q = qList.get(0); assertEquals("sbb가 무엇인가요?", q.getSubject());
		 */
         
		/*
		 * Optional<Question> oq = this.questionRepository.findById(1);
		 * assertTrue(oq.isPresent()); Question q = oq.get(); q.setSubject("수정된 제목");
		 * this.questionRepository.save(q);
		 */
         
//         assertEquals(2, this.questionRepository.count());
//         Optional<Question> oq = this.questionRepository.findById(1);
//         assertTrue(oq.isPresent());
//         Question q = oq.get();
//         this.questionRepository.delete(q);
//         assertEquals(1, this.questionRepository.count());
         
//         Optional<Question> oq = this.questionRepository.findById(2);
//         assertTrue(oq.isPresent());
//         Question q = oq.get();
//
//         Answer a = new Answer();
//         a.setContent("네 자동으로 생성됩니다.");
//         a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
//         a.setCreateDate(LocalDateTime.now());
//         this.answerRepository.save(a);
         
//         Optional<Answer> oa = this.answerRepository.findById(1);
//         assertTrue(oa.isPresent());
//         Answer a = oa.get();
//         assertEquals(2, a.getQuestion().getId());
         
         Optional<Question> oq = this.questionRepository.findById(2);
         assertTrue(oq.isPresent());
         Question q = oq.get();

         List<Answer> answerList = q.getAnswerList();

         assertEquals(1, answerList.size());
         assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
        
    }
    
    
	/*
	 * @Autowired 애너테이션을 더 알아보자. 
	 * 앞서 작성한 테스트 코드를 보면 questionRepository 변수는 선언만 되어 있고
	 * 그 값이 비어 있다. 하지만 @Autowired 애너테이션을 해당 변수에 적용하면 스프링 부트가 questionRepository 객체를
	 * 자동으로 만들어 주입한다. 객체를 주입하는 방식에는 @Autowired 애너테이션을 사용하는 것 외에 Setter 메서드 또는 생성자를
	 * 사용하는 방식이 있다. 순환 참조 문제와 같은 이유로 개발 시 @Autowired보다는 생성자를 통한 객체 주입 방식을 권장한다. 하지만
	 * 테스트 코드의 경우 JUnit이 생성자를 통한 객체 주입을 지원하지 않으므로 테스트 코드 작성 시에만 @Autowired를 사용하고 실제
	 * 코드 작성 시에는 생성자를 통한 객체 주입 방식을 사용해 보자.
	 */
    
	/*
	 * 질문을 조회한 후 이 질문에 달린 답변 전체를 구하는 테스트 코드이다. id가 2인 질문 데이터에 답변 데이터를 1개 등록했으므로 이와
	 * 같이 코드를 작성해 확인할 수 있다.
	 * 
	 * 그런데 이 코드를 실행하면 표시한 위치에 다음과 같은 오류가 발생한다.
	 * 
	 * 
	 * 
	 * 왜냐하면 QuestionRepository가 findById 메서드를 통해 Question 객체를 조회하고 나면 DB 세션이 끊어지기
	 * 때문이다.
	 * 
	 * DB 세션이란 스프링 부트 애플리케이션과 데이터베이스 간의 연결을 뜻한다.
	 * 
	 * 그래서 그 이후에 실행되는 q.getAnswerList() 메서드(Question 객체로부터 answer 리스트를 구하는 메서드)는 세션이
	 * 종료되어 오류가 발생한다. answerList는 앞서 q 객체를 조회할 때가 아니라 q.getAnswerList() 메서드를 호출하는
	 * 시점에 가져오기 때문에 이와 같이 오류가 발생한 것이다.
	 * 
	 * 이렇게 데이터를 필요한 시점에 가져오는 방식을 지연(Lazy) 방식이라고 한다. 이와 반대로 q 객체를 조회할 때 미리 answer
	 * 리스트를 모두 가져오는 방식은 즉시(Eager) 방식이라고 한다. @OneToMany, @ManyToOne 애너테이션의 옵션으로
	 * fetch=FetchType.LAZY 또는 fetch=FetchType.EAGER처럼 가져오는 방식을 설정할 수 있는데, 이 책에서는 따로
	 * 지정하지 않고 항상 기본값(디폴트값)을 사용한다.
	 * 
	 * 사실 이 문제는 테스트 코드에서만 발생한다. 실제 서버에서 JPA 프로그램들을 실행할 때는 DB 세션이 종료되지 않아 이와 같은 오류가
	 * 발생하지 않는다.
	 * 
	 * 테스트 코드를 수행할 때 이런 오류를 방지할 수 있는 가장 간단한 방법은 다음과 같이 @Transactional 애너테이션을 사용하는
	 * 것이다. @Transactional 애너테이션을 사용하면 메서드가 종료될 때까지 DB 세션이 유지된다. 코드를 수정해 보자.
	 */
}
