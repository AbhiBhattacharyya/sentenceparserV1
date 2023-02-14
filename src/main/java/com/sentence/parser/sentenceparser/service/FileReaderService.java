package com.sentence.parser.sentenceparser.service;

import com.sentence.parser.sentenceparser.model.Sentence;
import static com.sentence.parser.sentenceparser.constant.Constants.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileReaderService {
    @Value("classpath:large.in")
    Resource resource;


    @PostConstruct
    public Map<Integer,Sentence> readFromFile() throws IOException {
        File file = resource.getFile();
        Scanner myReader = new Scanner(file);
        Pattern pattern = Pattern.compile(PATTERN);
        Map<Integer,Sentence> sentenceMap = new  HashMap<Integer,Sentence>();
        int counter = 1;
        while (myReader.hasNextLine()) {
            Optional<String> line = Optional.ofNullable(myReader.nextLine());
            if(line.isPresent() && !line.isEmpty()){
                String[] words = line.get().split("\\s+");
                Sentence sentence = new Sentence();
                sentence.setWords(Arrays.stream(words)
                        .filter(pattern.asPredicate())
                        .sorted((w1,w2)->w1.compareToIgnoreCase(w2))
                        .collect(Collectors.toList()));

                if(!sentence.getWords().isEmpty()) {
                    sentenceMap.put(counter++, sentence);
                }
            }
        }
        log.info(""+sentenceMap.size());
        return sentenceMap;
    }
}
