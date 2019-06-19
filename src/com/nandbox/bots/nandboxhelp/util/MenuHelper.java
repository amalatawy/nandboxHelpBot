package com.nandbox.bots.nandboxhelp.util;

import static com.nandbox.bots.nandboxhelp.util.Constant.CREATE_CHANNEL_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.CREATE_GROUP_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.DELETE_GROUP_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.DOWNLOAD_STICKERS_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.RECALL_MSG_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.SHARE_LOCATION_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.USE_STICKERS_MEDIA_LINK;

import java.util.ArrayList;

import com.nandbox.bots.api.data.Button;
import com.nandbox.bots.api.data.Menu;
import com.nandbox.bots.api.data.Row;
import com.nandbox.bots.api.outmessages.SetChatMenuOutMessage;

public class MenuHelper {

	public static String FAQ_LINK = "https://nandbox.com/faq";

	public static final String MAIN_MENU_REF = "MainMenu";
	private static final String CHANNELS_MENU_REF = "ChannelsMenu";
	private static final String STICKERS_MENU_REF = "StickersMenu";

	public SetChatMenuOutMessage createMainMenuMessage(String chatId) {

		SetChatMenuOutMessage setChatMainMenuMsg = new SetChatMenuOutMessage();
		Button channelsBtn = createButton("Channels and Groups", "channelsCB", 1, "#b8e986", "Black", null,
				CHANNELS_MENU_REF, null);
		channelsBtn.setButtonIcon("ic_outline_rss_feed_24dp");
		channelsBtn.setButtonIconBgColor("#2F573F");

		Button stickersBtn = createButton("Stickers", "stickersCB", 2, "#b8e986", "Black", null, STICKERS_MENU_REF,
				null);
		stickersBtn.setButtonIcon("ic_mood_24dp");
		stickersBtn.setButtonIconBgColor("#2F573F");

		Button recallMsgBtn = createButton("Recall Message", "RecallMessageCB", 3, "#b8e986", "Black", null,
				MAIN_MENU_REF, RECALL_MSG_MEDIA_LINK);
		recallMsgBtn.setButtonIcon("ic_delete_forever_24dp");
		recallMsgBtn.setButtonIconBgColor("#2F573F");

		Button shareLocationBtn = createButton("Share Location", "ShareLocationCB", 4, "#b8e986", "Black", null,
				MAIN_MENU_REF, SHARE_LOCATION_MEDIA_LINK);
		shareLocationBtn.setButtonIcon("ic_room_24dp");
		shareLocationBtn.setButtonIconBgColor("#2F573F");

		Button faqBtn = createButton("FAQ", "FAQCB", 5, "#29ba65", "white", null, MAIN_MENU_REF, FAQ_LINK);
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
				CHANNELS_MENU_REF, CREATE_CHANNEL_MEDIA_LINK);

		Button createGroupBtn = createButton("Create Group", "CreateGroupCB", 2, "#478de5", "White", null,
				CHANNELS_MENU_REF, CREATE_GROUP_MEDIA_LINK);

		Button deleteGroupBtn = createButton("Delete Group", "DeleteGroupCB", 3, "#478de5", "White", null,
				CHANNELS_MENU_REF, DELETE_GROUP_MEDIA_LINK);

		Button backBtn = createButton("Back", "BackCB", 4, "#33649d", "White", null, MAIN_MENU_REF, null);
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
				STICKERS_MENU_REF, USE_STICKERS_MEDIA_LINK);
		Button downloadStickersBtn = createButton("Download Stickers", "DownloadStickersCB", 2, "#edad43", "White",
				null, STICKERS_MENU_REF, DOWNLOAD_STICKERS_MEDIA_LINK);
		Button backBtn = createButton("Back", "BackCB", 3, "#bd7700", "White", null, MAIN_MENU_REF, null);
		row.setButtons(new Button[] { useStickersBtn, downloadStickersBtn });
		backRow.setButtons(new Button[] { backBtn });
		stickersMenu.setRows(new Row[] { row, backRow });
		return stickersMenu;
	}

	public Button createButton(String label, String callback, int order, String bgColor, String txtColor,
			String buttonQuery, String nextMenuRef, String buttonURL) {
		Button btn = new Button();
		btn.setButtonLabel(label);
		btn.setButtonOrder(order);
		btn.setButtonCallBack(callback);
		btn.setButtonBgColor(bgColor);
		btn.setButtonTextColor(txtColor);
		btn.setButtonQuery(buttonQuery);
		btn.setNextMenu(nextMenuRef);
		btn.setButtonURL(buttonURL);
		return btn;
	}

}
