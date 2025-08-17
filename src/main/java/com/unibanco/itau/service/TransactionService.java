package com.unibanco.itau.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.unibanco.itau.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

@Service
public class TransactionService {
    //"src/main/resources/db/database.json"
    @Autowired
    public DateConversion conversion;
    public ObjectMapper mapper = new ObjectMapper();
    final CollectionType LINKED_LIST_OF_TRANSACTIONS = mapper
            .getTypeFactory()
            .constructCollectionType(LinkedList.class, TransactionDTO.class);

    public void write(TransactionDTO data, String path) throws IOException {
        File file_writer = new File(path);
        LinkedList<TransactionDTO> existing = this.read(path);
        existing.add(conversion.convert(data));
        mapper.writeValue(file_writer, data);
    }
    public LinkedList<TransactionDTO> read(String path) throws IOException {
        File file_reader = new File(path);
        return mapper.readValue(file_reader, LINKED_LIST_OF_TRANSACTIONS);
    }
}
