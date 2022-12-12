package com.ynovakova.contacts.web;

import com.ynovakova.contacts.pojo.Contact;
import com.ynovakova.contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("contact/all")
    public ResponseEntity<List<Contact>> getContacts(){
        List<Contact> contacts = contactService.getContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<HttpStatus> createContact(@Valid @RequestBody Contact contact){
        contactService.saveContact(contact);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @Valid @RequestBody Contact contact){
        contactService.updateContact(id, contact);
        return new ResponseEntity<Contact>(contactService.getContactById(id), HttpStatus.OK);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id){
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
