package com.khrystoforov.adaptivetesting.file.service.impl;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.answerOption.service.AnswerOptionService;
import com.khrystoforov.adaptivetesting.file.service.ParserService;
import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.question.service.QuestionService;
import com.khrystoforov.adaptivetesting.topic.model.Topic;
import com.khrystoforov.adaptivetesting.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@RequiredArgsConstructor
public class ExcelParserService implements ParserService {
    private final TopicService topicService;
    private final QuestionService questionService;
    private final AnswerOptionService answerOptionService;

    @Override
    public void parseAndSave(MultipartFile file, String topicName) throws Exception {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Topic topic = topicService.createIfNotExistsByName(topicName);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                String questionText = row.getCell(0).getStringCellValue().trim();
                String optionsRaw = row.getCell(1).getStringCellValue().trim();
                String correctAnswer = row.getCell(2).getStringCellValue().trim();
                BigDecimal difficulty = BigDecimal.valueOf(row.getCell(3).getNumericCellValue());
                BigDecimal discrimination = BigDecimal.valueOf(row.getCell(4).getNumericCellValue());

                String[] options = optionsRaw.split("\\|");
                System.out.println("options length: " + options.length);
                BigDecimal guessing = BigDecimal.ONE.divide(BigDecimal.valueOf(options.length + 1), 2, RoundingMode.HALF_UP);

                Question question = new Question(questionText, difficulty, guessing, discrimination, topic);

                for (String optionText : options) {
                    AnswerOption answerOption = new AnswerOption(optionText.trim(), false);
                    question.addOption(answerOption);
                }
                question.addOption(new AnswerOption(correctAnswer.trim(), true));

                questionService.create(question);
            }

        }
    }
}
