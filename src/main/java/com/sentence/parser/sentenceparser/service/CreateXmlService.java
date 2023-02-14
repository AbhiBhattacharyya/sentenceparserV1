package com.sentence.parser.sentenceparser.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sentence.parser.sentenceparser.model.Sentence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class CreateXmlService {

public void createXMLFile(Sentence sentence) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(sentence);
        log.info(xml);
       // assertNotNull(xml);
    }
}
