package com.unibanco.itau.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.unibanco.itau.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

@Service
public class TransactionService {
    @Autowired
    public DateConversion conversion;
    public ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    final CollectionType LINKED_LIST_OF_TRANSACTIONS = mapper
            .getTypeFactory()
            .constructCollectionType(LinkedList.class, TransactionDTO.class);
    public TransactionService(DateConversion conversion){
        this.conversion = conversion;
    }
    public void write(TransactionDTO data, String path) throws IOException {
        File file_writer = new File(path);
        LinkedList<TransactionDTO> existing = this.read(path);
        existing.add(data);
        mapper.writeValue(file_writer, existing);
    }
    public LinkedList<TransactionDTO> read(String path) throws IOException {
        File file_reader = new File(path);
        if (!file_reader.exists()){
            return new LinkedList<>();
        }
        return mapper.readValue(file_reader, LINKED_LIST_OF_TRANSACTIONS);
    }
    public void deleteFile(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists()){
            throw new FileNotFoundException("File Not Found");
        }
        file.delete();
    }
}
