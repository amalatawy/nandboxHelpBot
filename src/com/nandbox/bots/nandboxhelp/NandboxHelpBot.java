package com.nandbox.bots.nandboxhelp;

import static com.nandbox.bots.nandboxhelp.util.Constant.CREATE_CHANNEL_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.CREATE_GROUP_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.DELETE_GROUP_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.DOWNLOAD_STICKERS_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.RECALL_MSG_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.SHARE_LOCATION_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.Constant.USE_STICKERS_MEDIA_LINK;
import static com.nandbox.bots.nandboxhelp.util.MenuHelper.MAIN_MENU_REF;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.nandbox.bots.api.Nandbox;
import com.nandbox.bots.api.NandboxClient;
import com.nandbox.bots.api.data.Chat;
import com.nandbox.bots.api.data.User;
import com.nandbox.bots.api.inmessages.BlackList;
import com.nandbox.bots.api.inmessages.ChatAdministrators;
import com.nandbox.bots.api.inmessages.ChatMember;
import com.nandbox.bots.api.inmessages.ChatMenuCallback;
import com.nandbox.bots.api.inmessages.IncomingMessage;
import com.nandbox.bots.api.inmessages.InlineMessageCallback;
import com.nandbox.bots.api.inmessages.InlineSearch;
import com.nandbox.bots.api.inmessages.MessageAck;
import com.nandbox.bots.api.inmessages.PermanentUrl;
import com.nandbox.bots.api.inmessages.WhiteList;
import com.nandbox.bots.api.util.Utils;
import com.nandbox.bots.nandboxhelp.util.MenuHelper;

import net.minidev.json.JSONObject;

public class NandboxHelpBot {

	public static void main(String[] args) throws Exception {

		String token = NandboxHelpBot.getTokenFromPropFile();

		NandboxClient client = NandboxClient.get();
		client.connect(token, new Nandbox.Callback() {
			Nandbox.Api api = null;

			@Override
			public void onConnect(Nandbox.Api api) {
				System.out.println("ONCONNECT");
				this.api = api;
			}

			@Override
			public void onReceive(JSONObject obj) {
			}

			@Override
			public void onClose() {
				System.out.println("ONCLOSE");
			}

			@Override
			public void onError() {
				System.out.println("ONERROR");
			}

			@Override
			public void onReceive(IncomingMessage incomingMsg) {
				sendBotMenuWithNavigationButton(incomingMsg.getChat().getId());
				api.sendText(incomingMsg.getChat().getId(), "Please use Bot Menu");
			}

			@Override
			public void onInlineMessageCallback(InlineMessageCallback inlineMsgCallback) {
			}

			@Override
			public void onChatMenuCallBack(ChatMenuCallback chatMenuCallback) {
				System.out.println(chatMenuCallback.toJsonObject());
				String chatId = chatMenuCallback.getChat().getId();

				// Group And Channel

				if (chatMenuCallback.getButtonCallback().equals("CreateChannelCB")) {

					api.sendText(chatId, CREATE_CHANNEL_MEDIA_LINK);

				}

				if (chatMenuCallback.getButtonCallback().equals("CreateGroupCB")) {

					api.sendText(chatId, CREATE_GROUP_MEDIA_LINK);
				}

				if (chatMenuCallback.getButtonCallback().equals("DeleteGroupCB")) {

					api.sendText(chatId, DELETE_GROUP_MEDIA_LINK);
				}

				// Stickers
				if (chatMenuCallback.getButtonCallback().equals("UseStickersCB")) {

					api.sendText(chatId, USE_STICKERS_MEDIA_LINK);

				}

				if (chatMenuCallback.getButtonCallback().equals("DownloadStickersCB")) {

					api.sendText(chatId, DOWNLOAD_STICKERS_MEDIA_LINK);

				}

				// Recall
				if (chatMenuCallback.getButtonCallback().equals("RecallMessageCB")) {

					api.sendText(chatId, RECALL_MSG_MEDIA_LINK);
				}

				// ShareLocation
				if (chatMenuCallback.getButtonCallback().equals("ShareLocationCB")) {

					api.sendText(chatId, SHARE_LOCATION_MEDIA_LINK);
				}
			}

			@Override
			public void onChatAdministrators(ChatAdministrators chatAdministrators) {
			}

			@Override
			public void onChatMember(ChatMember chatMember) {
			}

			private void sendBotMenuWithNavigationButton(String chatId) {
//				Utils utils = new Utils();
				Utils.setNavigationButton(chatId, MAIN_MENU_REF, api);
				MenuHelper utility = new MenuHelper();
				api.send(utility.createMainMenuMessage(chatId));
				
			}

			@Override
			public void onChatDetails(Chat chat) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onInlineSearh(InlineSearch inlineSearch) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMessagAckCallback(MessageAck msgAck) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMyProfile(User user) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserDetails(User user) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUserJoinedBot(User user) {
				sendBotMenuWithNavigationButton(user.getId());

			}

			@Override
			public void permanentUrl(PermanentUrl permenantUrl) {
				// TODO Auto-generated method stub

			}

			@Override
			public void userLeftBot(User user) {
				// TODO Auto-generated method stub

			}

			@Override
			public void userStartedBot(User user) {
				sendBotMenuWithNavigationButton(user.getId());

			}

			@Override
			public void userStoppedBot(User user) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onBlackList(BlackList blackList) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onWhiteList(WhiteList whiteList) {
				// TODO Auto-generated method stub
				
			}

		});

	}

	private static String getTokenFromPropFile() throws IOException {
		Properties prop = new Properties();
		InputStream input = new FileInputStream("token.properties");
		prop.load(input);
		input.close();
		return prop.getProperty("Token");
	}
}
