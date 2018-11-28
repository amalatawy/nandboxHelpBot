package com.nandbox.bots.nandboxhelp.util;

import java.util.ArrayList;

import com.nandbox.bots.api.data.Button;
import com.nandbox.bots.api.data.Menu;
import com.nandbox.bots.api.data.Row;
import com.nandbox.bots.api.outmessages.ChatMenuOutMessage;

public class MenuHelper {

	public static String FAQ_LINK = "https://nandbox.com/faq";

	public static final String MAIN_MENU_REF = "MainMenu";
	private static final String CHANNELS_MENU_REF = "ChannelsMenu";
	private static final String STICKERS_MENU_REF = "StickersMenu";

	public ChatMenuOutMessage createMainMenuMessage(String chatId) {

		ChatMenuOutMessage setChatMainMenuMsg = new ChatMenuOutMessage();
		Button channelsBtn = createButton("Channels and Groups", "channelsCB", 1, "#b8e986", "Black", null,
				CHANNELS_MENU_REF);
		Button stickersBtn = createButton("Stickers", "stickersCB", 2, "#b8e986", "Black", null, STICKERS_MENU_REF);
		Button recallMsgBtn = createButton("Recall Message", "RecallMessageCB", 3, "#b8e986", "Black", null,
				MAIN_MENU_REF);
		Button shareLocationBtn = createButton("Share Location", "ShareLocationCB", 4, "#b8e986", "Black", null,
				MAIN_MENU_REF);
		Button faqBtn = createButton("FAQ", "FAQCB", 5, "#29ba65", "white", null, MAIN_MENU_REF);
		faqBtn.setButtonURL(FAQ_LINK);
		ArrayList<Menu> mainMenus = new ArrayList<Menu>();
		Row firstRow = new Row();
		firstRow.setRowOrder(1);
		firstRow.setButtons(new Button[] { channelsBtn, stickersBtn });
		Row secondRow = new Row();
		secondRow.setRowOrder(2);
		secondRow.setButtons(new Button[] { recallMsgBtn, shareLocationBtn });
		Row faqRow = new Row();
		faqRow.setRowOrder(3);
		faqRow.setButtons(new Button[] { faqBtn });
		Menu chatMainMenu = new Menu();
		chatMainMenu.setMenuRef(MAIN_MENU_REF);
		chatMainMenu.setRows(new Row[] { firstRow, secondRow, faqRow });
		mainMenus.add(chatMainMenu);
		mainMenus.add(createChannelsSubMenus());
		mainMenus.add(createStickersSubMenus());
		setChatMainMenuMsg.setMenus(mainMenus.toArray(new Menu[mainMenus.size()]));
		setChatMainMenuMsg.setChatId(chatId);
		return setChatMainMenuMsg;

	}
	private Menu createChannelsSubMenus() {

		Menu channelsMenu = new Menu();
		channelsMenu.setMenuRef("ChannelsMenu");
		Row firstRow = new Row();
		Row secondRow = new Row();
		Row backRow = new Row();

		Button createChannelBtn = createButton("Create Channel", "CreateChannelCB", 1, "#478de5", "White", null,
				CHANNELS_MENU_REF);

		Button createGroupBtn = createButton("Create Group", "CreateGroupCB", 2, "#478de5", "White", null,
				CHANNELS_MENU_REF);

		Button deleteGroupBtn = createButton("Delete Group", "DeleteGroupCB", 3, "#478de5", "White", null,
				CHANNELS_MENU_REF);

		Button backBtn = createButton("Back", "BackCB", 4, "#33649d", "White", null, MAIN_MENU_REF);
		firstRow.setButtons(new Button[] { createChannelBtn, createGroupBtn });
		secondRow.setButtons(new Button[] { deleteGroupBtn });
		backRow.setButtons(new Button[] { backBtn });
		channelsMenu.setRows(new Row[] { firstRow, secondRow, backRow });
		return channelsMenu;
	}

	private Menu createStickersSubMenus() {

		Menu stickersMenu = new Menu();
		stickersMenu.setMenuRef("StickersMenu");
		Row row = new Row();
		Row backRow = new Row();
		Button useStickersBtn = createButton("Use Stickers", "UseStickersCB", 1, "#edad43", "White", null,
				STICKERS_MENU_REF);
		Button downloadStickersBtn = createButton("Download Stickers", "DownloadStickersCB", 2, "#edad43", "White",
				null, STICKERS_MENU_REF);
		Button backBtn = createButton("Back", "BackCB", 3, "#bd7700", "White", null, MAIN_MENU_REF);
		row.setButtons(new Button[] { useStickersBtn, downloadStickersBtn });
		backRow.setButtons(new Button[] { backBtn });
		stickersMenu.setRows(new Row[] { row, backRow });
		return stickersMenu;
	}
	
	private Button createButton(String label, String callback, int order, String bgColor, String txtColor,
			String buttonQuery, String nextMenuRef) {
		Button btn = new Button();
		btn.setButtonLabel(label);
		btn.setButtonOrder(order);
		btn.setButtonCallBack(callback);
		btn.setButtonBgColor(bgColor);
		btn.setButtonTextColor(txtColor);
		btn.setButtonQuery(buttonQuery);
		btn.setNextMenu(nextMenuRef);
		return btn;
	}

}
