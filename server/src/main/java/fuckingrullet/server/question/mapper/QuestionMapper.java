package fuckingrullet.server.question.mapper;


import fuckingrullet.server.answer.mapper.AnswerMapper;
import fuckingrullet.server.answer.service.AnswerService;
import fuckingrullet.server.domain.Answer;
import fuckingrullet.server.domain.Member;
import fuckingrullet.server.domain.Question;
import fuckingrullet.server.exception.BusinessLogicException;
import fuckingrullet.server.member.mapper.MemberMapper;
import fuckingrullet.server.member.service.MemberService;
import fuckingrullet.server.question.dto.*;
import fuckingrullet.server.question.service.QuestionService;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    default Question questionPostDtoToQuestion(MemberService memberService, QuestionPostDto questionPostDto){
        Question question = new Question();

        question.setTitle(questionPostDto.getTitle());
        question.setArticle(questionPostDto.getArticle());
        question.setViews(0);
        question.getCreateAt();
        question.getModifiedAt();
        /*question.setMember(memberService.getLoginMember);*/

        return question;

    }

    default Question questionPatchDtoToQuestion(QuestionService questionService, QuestionPatchDto questionPatchDto){
        Question question = new Question();
        question.setQuestionId(questionPatchDto.getQuestionId());
        question.setTitle(questionPatchDto.getTitle());
        question.setArticle(questionPatchDto.getArticle());

        return question;
    }

    default QuestionResponseDto questionToQuestionResponseDto(MemberMapper memberMapper, Question question){
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setQuestionId(question.getQuestionId());
        questionResponseDto.setTitle(question.getTitle());
        questionResponseDto.setArticle(question.getArticle());
        questionResponseDto.setViews(question.getViews());
        questionResponseDto.setCreateAt(question.getCreateAt());
        questionResponseDto.setModifiedAt(question.getModifiedAt());

//        Member member = question.getMember();
        /*questionResponseDto.setMember(memberMapper.memberRegisterDtoToMember(member));*/
        return questionResponseDto;
    }

    List<QuestionResponseDto> questionToQuestionResponseDtos(List <Question> questions);

    default QuestionAndAnswerResponseDto questionToQuestionAndAnswerResponseDto(AnswerService answerService, AnswerMapper answerMapper,
                                                                                Question question, Integer answerPage, Integer answerSize, String answerSort){
        QuestionAndAnswerResponseDto questionAndAnswerResponseDto = new QuestionAndAnswerResponseDto();
        questionAndAnswerResponseDto.setQuestionId(question.getQuestionId());
        questionAndAnswerResponseDto.setTitle(question.getTitle());
        questionAndAnswerResponseDto.setArticle(question.getArticle());
        questionAndAnswerResponseDto.setViews(question.getViews());
        questionAndAnswerResponseDto.setCreateAt(question.getCreateAt());
        questionAndAnswerResponseDto.setModifiedAt(question.getModifiedAt());

        try{
            Page<Answer> pageAnswers = answerService.findAnswers(question,answerPage,answerSize,answerSort);
            List<Answer> answers = pageAnswers.getContent();
            questionAndAnswerResponseDto.setAnswers(new MultiResponseDto<>(answerMapper.answersToAnswerResponseDtos(answers),pageAnswers));
        }catch (BusinessLogicException e){}
        return questionAndAnswerResponseDto;
    }
}
