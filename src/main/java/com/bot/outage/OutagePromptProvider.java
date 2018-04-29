package com.bot.outage;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class OutagePromptProvider implements PromptProvider {
	@Override
	public AttributedString getPrompt() {
	    return new AttributedString("outage-bot:>",
	    		AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
	}
}
