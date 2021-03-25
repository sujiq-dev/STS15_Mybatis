package com.lec.sts15_mybatis.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	
	//ticket구매 form입니다. 어디에 있는거? ticket foler밑에 있는 buy_ticket.jsp
	@RequestMapping("/buy_ticket")
	public void buy_ticket() {}
	
	
	// 티켓 구매 처리 (트랜잭션)
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(TicketDTO dto, Model model) {
		System.out.println( "/buy_ticket_card" );
		System.out.println( "user id : " + dto.getUserId());
		System.out.println( "ticket count : " + dto.getTicketCount());

		String page = "ticket/buy_ticket_done";
		try {			
			ticketService.buyTicket(dto);  // 트랜잭션 수행!!
			model.addAttribute("ticketInfo", dto);
		} catch(Exception e) {
			e.printStackTrace();  
			page = "ticket/buy_ticket_fail";  // 트랜잭션 오류 발생시..
		}
		return page;
	}


	
	
}
