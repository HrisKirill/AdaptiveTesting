package com.khrystoforov.adaptivetesting.file.service.impl;

import com.khrystoforov.adaptivetesting.answerOption.model.AnswerOption;
import com.khrystoforov.adaptivetesting.file.service.ParserService;
import com.khrystoforov.adaptivetesting.question.model.Question;
import com.khrystoforov.adaptivetesting.question.service.QuestionService;
import com.khrystoforov.adaptivetesting.topic.model.Topic;
import com.khrystoforov.adaptivetesting.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
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

    @Override
    public void parseAndSave(MultipartFile file, String topicName) throws Exception {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Topic topic = topicService.createIfNotExistsByName(topicName);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                String questionText = getCellValue(row, 0);
                String optionsRaw = getCellValue(row, 1);
                String correctAnswer = getCellValue(row, 2);
                BigDecimal difficulty = new BigDecimal(getCellValue(row, 3));
                BigDecimal validatedDifficulty = difficulty.min(BigDecimal.valueOf(2.0)).max(BigDecimal.valueOf(-2.0));
                BigDecimal discrimination = new BigDecimal(getCellValue(row, 4));

                String[] options = optionsRaw.split(";");
                BigDecimal guessing = BigDecimal.ONE.divide(BigDecimal.valueOf(options.length + 1), 2, RoundingMode.HALF_UP);

                Question question = new Question(questionText, validatedDifficulty, guessing, discrimination, topic);

                for (String optionText : options) {
                    AnswerOption answerOption = new AnswerOption(optionText.trim(), false);
                    question.addOption(answerOption);
                }
                question.addOption(new AnswerOption(correctAnswer.trim(), true));

                questionService.create(question);
            }

        }
    }

    private static String getCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        return (cell == null) ? "" : cell.toString().trim();
    }
}
