package moe.dic1911.esun_library.controller;

import moe.dic1911.esun_library.repository.BookRepository;
import moe.dic1911.esun_library.repository.BorrowLogRepository;
import moe.dic1911.esun_library.repository.InventoryRepository;
import moe.dic1911.esun_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import static moe.dic1911.esun_library.config.Constants.attributes;

public class BaseController {
    @Autowired
    BookRepository bookRepo;
    @Autowired
    InventoryRepository inventoryRepo;
    @Autowired
    BorrowLogRepository borrowLogRepository;

    @Autowired
    UserRepository userRepo;
    protected void addCommonAttribute(Model model) {
        model.addAllAttributes(attributes);
    }
}
