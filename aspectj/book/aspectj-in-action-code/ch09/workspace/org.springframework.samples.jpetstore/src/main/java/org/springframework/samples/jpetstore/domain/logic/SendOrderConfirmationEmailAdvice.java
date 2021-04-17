/*
Copyright 2009 Ramnivas Laddad

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 
    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/

package org.springframework.samples.jpetstore.domain.logic;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.samples.jpetstore.domain.Account;
import org.springframework.samples.jpetstore.domain.Order;

/**
 * AOP advice that sends confirmation email after order has been submitted
 * @author Dmitriy Kopylenko
 */
public class SendOrderConfirmationEmailAdvice implements AfterReturningAdvice, InitializingBean {

	private static final String DEFAULT_MAIL_FROM = "jpetstore@springframework.org";

	private static final String DEFAULT_SUBJECT = "Thank you for your order!";

	private final Log logger = LogFactory.getLog(getClass());

	private MailSender mailSender;

	private String mailFrom = DEFAULT_MAIL_FROM;

	private String subject = DEFAULT_SUBJECT;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void afterPropertiesSet() throws Exception {
		if (this.mailSender == null) {
			throw new IllegalStateException("mailSender is required");
		}
	}

	public void afterReturning(Object returnValue, Method m, Object[] args, Object target) throws Throwable {
		Order order = (Order) args[0];
		Account account = ((PetStoreFacade) target).getAccount(order.getUsername());

		// don't do anything if email address is not set
		if (account.getEmail() == null || account.getEmail().length() == 0) {
			return;
		}

		StringBuffer text = new StringBuffer();
		text.append("Dear ").append(account.getFirstName()).append(' ').append(account.getLastName());
		text.append(", thank your for your order from JPetStore. Please note that your order number is ");
		text.append(order.getOrderId());

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(account.getEmail());
		mailMessage.setFrom(this.mailFrom);
		mailMessage.setSubject(this.subject);
		mailMessage.setText(text.toString());
		try {
			this.mailSender.send(mailMessage);
		}
		catch (MailException ex) {
			// just log it and go on
			logger.warn("An exception occured when trying to send email", ex);
		}
	}

}
