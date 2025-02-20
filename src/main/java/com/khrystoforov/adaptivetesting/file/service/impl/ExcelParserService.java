package com.khrystoforov.adaptivetesting.file.service.impl;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.answerOption.service.AnswerOptionService;
import com.khrystoforov.adaptivetesting.file.service.ParserService;
import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.question.service.QuestionService;
import com.khrystoforov.adaptivetesting.topic.model.Topic;
import com.khrystoforov.adaptivetesting.topic.service.TopicService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;


@Service
public class ExcelParserService implements ParserService {
    private final TopicService topicService;
    private final QuestionService questionService;
    private final AnswerOptionService answerOptionService;

    public ExcelParserService(TopicService topicService, QuestionService questionService, AnswerOptionService answerOptionService) {
        this.topicService = topicService;
        this.questionService = questionService;
        this.answerOptionService = answerOptionService;
    }

    @Override
    public void parseAndSave(MultipartFile file) throws Exception {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String topicName = row.getCell(0).getStringCellValue().trim();
                String questionText = row.getCell(1).getStringCellValue().trim();
                String optionsRaw = row.getCell(2).getStringCellValue().trim();
                String correctAnswer = row.getCell(3).getStringCellValue().trim();
                int difficulty = (int) row.getCell(4).getNumericCellValue();

                Topic topic = topicService.createIfNotExistsByName(topicName);

                Question question = new Question(questionText, difficulty, topic);
                question = questionService.create(question);

                String[] options = optionsRaw.split(";");
                Set<AnswerOption> answerOptions = new HashSet<>();

                for (String optionText : options) {
                    AnswerOption answerOption = new AnswerOption(optionText.trim(), false, question);
                    answerOptions.add(answerOption);
                }
                answerOptions.add(new AnswerOption(correctAnswer.trim(), true, question));

                answerOptionService.saveAll(answerOptions);
            }
        }
    }
}
