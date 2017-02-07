package com.library.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.library.Constants;
import com.library.entity.Book;
import com.library.entity.Librarian;
import com.library.entity.Reader;
import com.library.entity.UserAbstract;
import com.library.enums.Role;
import com.library.facade.LibraryFacade;
import com.library.service.iface.AutoLoginService;
import com.library.service.iface.BookService;
import com.library.service.iface.LibrarianService;
import com.library.service.iface.ReaderService;
import com.library.service.iface.ReadingService;
import com.library.service.impl.FacadeLibrarySirviceImpl;

@RestController
public class Controller {
	@Autowired
	private ReaderService readerService;
	@Autowired
	private LibrarianService librarianService;
	@Autowired
	private BookService bookService;
	@Autowired
	private AutoLoginService securityService;
	@Autowired
	private FacadeLibrarySirviceImpl facadeService;
	@Autowired
	private ReadingService readingService;
	
	// give reader book
	@RequestMapping(value = "/librarian/books/work/give", method = RequestMethod.POST)
	public void giveBook(@ModelAttribute("logReader") String loginReader, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String loginLibrarian = SecurityContextHolder.getContext().getAuthentication().getName();
		String[] idBooks = (String[]) request.getParameterValues("flagB");
		Reader reader = readerService.findByLogin(loginReader);
		if(idBooks!=null && reader!=null && loginLibrarian!=null){
			Librarian librarian = librarianService.findByLogin(loginLibrarian);
			request.getSession().setAttribute("errorL", "");
			readingService.addAll(librarian, reader, idBooks);
		} else {
			request.getSession().setAttribute("errorL", "Error data");
		}
		response.sendRedirect(Constants.REF_START_L);
	}
	// in library book
	@RequestMapping(value = "/librarian/books/work/return", method = RequestMethod.POST)
	public void returnBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] idReadings = (String[]) request.getParameterValues("flagR");
		if(idReadings!=null){
			readingService.returnInLibraryAll(idReadings);
		}
		response.sendRedirect(Constants.REF_START_L);
	}
		
	// generation facade librarian page
	@RequestMapping(value = "/librarian/books/work")
	public ModelAndView modelLibrarian() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Librarian librarian = librarianService.findByLogin(login);
		LibraryFacade facade = new LibraryFacade();
		facadeService.initFacadeLibrary(facade);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homeLibrarian");
		mav.addObject("librarian", librarian);
		mav.addObject("facade", facade);
		return mav;
	}
	// generation reader page
	@RequestMapping(value = "/reader/view/books")
	public ModelAndView modelReader() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Reader reader = readerService.findByLogin(login);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homeReader");
		mav.addObject("reader", reader);
		return mav;
	}
	
	//redirect user start page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void home(HttpServletResponse response, HttpSession session) throws IOException {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Reader reader = readerService.findByLogin(login);
		Librarian librarian = librarianService.findByLogin(login);
		if (reader!=null) {
			response.sendRedirect(Constants.REF_START_R);
		} else if (librarian!=null) {
			response.sendRedirect(Constants.REF_START_L);
		} else {
			response.sendRedirect(Constants.REF_START);
		}
	}
	//registration reader
	@RequestMapping(value = "/registration/reader/add", method = RequestMethod.POST)
	public void registrationReader(@ModelAttribute("reader") Reader reader,
			HttpServletResponse response, HttpSession session) throws IOException {
		if (!validateUser(reader)) {
			session.setAttribute("error", "Error data");
			response.sendRedirect(Constants.REF_REG_READ);
			return;
		}
		readerService.saveReader(reader);
		securityService.autoLogin(reader.getLogin(), reader.getPassword(), Role.READER);
		session.setAttribute("reader", reader);
		response.sendRedirect(Constants.REF_START_R);
	}
	//registration librarian
	@RequestMapping(value = "/registration/librarian/add", method = RequestMethod.POST)
	public void registrationLibrarian(@ModelAttribute("librarian") Librarian librarian,
			HttpServletResponse response, HttpSession session) throws IOException {
		if (!validateUser(librarian)) {
			session.setAttribute("error", "Error data");
			response.sendRedirect(Constants.REF_REG_LIBR);
			return;
		}
		session.setAttribute("error", "");
		librarianService.saveLibrarian(librarian);
		securityService.autoLogin(librarian.getLogin(), librarian.getPassword(), Role.LIBRARIAN);
		session.setAttribute("librarian", librarian);
		response.sendRedirect(Constants.REF_START_L);
	}
	// add new book 
	@RequestMapping(value = "/librarian/books/new/add", method = RequestMethod.POST)
	public void addBook(@ModelAttribute("book") Book book,
			HttpServletResponse response, HttpSession session) throws IOException {
		if (!validateBook(book)) {
			session.setAttribute("error", "Error data");
			response.sendRedirect(Constants.REF_NEW_BOOK);
			return;
		}
		bookService.addBook(book);
		response.sendRedirect(Constants.REF_START_L);
	}
	// custom validation
	private boolean validateUser(UserAbstract user) {
		if (user == null) {
			return false;
		}else if (user.getLogin() == null || user.getLogin()=="") {
			return false;
		} else if (user.getPassword() == null || user.getPassword()=="") {
			return false;
		}else if (readerService.findByLogin(user.getLogin()) != null) {
			return false;
		}else if (librarianService.findByLogin(user.getLogin()) != null) {
			return false;
		}
		return true;
	}
	private boolean validateBook(Book book) {
		if (book == null) {
			return false;
		}
		if (book.getName() == null || book.getName()=="") {
			return false;
		}
		if (book.getAuthor() == null || book.getAuthor()=="") {
			return false;
		}
		if (book.getYearPublish() == null || book.getYearPublish()=="") {
			return false;
		}
		return true;
	}
}
