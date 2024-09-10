package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne
    private SiteUser author;
	
	/*
	 * 엔티티의 속성 이름과 테이블의 열 이름의 차이를 알아보자 Question 엔티티에서 작성 일시에 해당하는 createDate 속성의 이름은
	 * 데이터베이스의 테이블에서는 create_date라는 열 이름으로 설정된다. 즉, createDate처럼 카멜 케이스(camel case)
	 * 형식의 이름은 create_date처럼 모두 소문자로 변경되고 단어가 언더바(_)로 구분되어 데이터베이스 테이블의 열 이름이 된다.
	 * 
	 * 카멜 케이스는 맨 첫 글자를 제외한 나머지 단어의 첫 글자를 대문자로 써 구분하는 작명 방식을 말한다.
	 * 
	 * 엔티티를 만들 때 Setter 메서드는 사용하지 않는다
	 * 
	 * 일반적으로 엔티티를 만들 때에는 Setter 메서드를 사용하지 않기를 권한다. 왜냐하면 엔티티는 데이터베이스와 바로 연결되므로 데이터를
	 * 자유롭게 변경할 수 있는 Setter 메서드를 허용하는 것이 안전하지 않다고 판단하기 때문이다. 그렇다면 Setter 메서드 없이 어떻게
	 * 엔티티에 값을 저장할 수 있을까?
	 * 
	 * 엔티티는 생성자에 의해서만 엔티티의 값을 저장할 수 있게 하고, 데이터를 변경해야 할 경우에는 메서드를 추가로 작성하면 된다. 다만, 이
	 * 책은 복잡도를 낮추고 원활한 설명을 위해 엔티티에 Setter 메서드를 추가하여 진행함을 기억해 두자.
	 */

}
