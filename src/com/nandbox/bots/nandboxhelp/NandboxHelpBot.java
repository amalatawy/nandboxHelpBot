package com.nandbox.bots.nandboxhelp;

import static com.nandbox.bots.nandboxhelp.util.MenuHelper.MAIN_MENU_REF;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.nandbox.bots.api.Nandbox;
import com.nandbox.bots.api.NandboxClient;
import com.nandbox.bots.nandboxhelp.util.Constant;
import com.nandbox.bots.nandboxhelp.util.MenuHelper;
import com.nandbox.bots.api.inmessages.ChatAdministrators;
import com.nandbox.bots.api.inmessages.ChatMember;
import com.nandbox.bots.api.inmessages.ChatMenuCallback;
import com.nandbox.bots.api.inmessages.IncomingMessage;
import com.nandbox.bots.api.inmessages.InlineMessageCallback;
import com.nandbox.bots.api.inmessages.Profile;
import com.nandbox.bots.api.util.Utils;

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
			}

			@Override
			public void onInlineMessageCallback(InlineMessageCallback inlineMsgCallback) {
			}

			@Override
			public void onMessagAckCallback(JSONObject obj) {
			}

			@Override
			public void onChatMenuCallBack(ChatMenuCallback chatMenuCallback) {
				System.out.println(chatMenuCallback.toJsonObject());
				String chatId = chatMenuCallback.getChat().getId();

				// Group And Channel

				if (chatMenuCallback.getButtonCallback().equals("CreateChannelCB")) {
					api.sendVideo(chatId, Constant.CREATE_CHANNEL_MEDIA_ID, "How to create a channel.");
				}

				if (chatMenuCallback.getButtonCallback().equals("CreateGroupCB")) {
					api.sendVideo(chatId, Constant.CREATE_GROUP_MEDIA_ID, "How to create a group.");
				}

				if (chatMenuCallback.getButtonCallback().equals("DeleteGroupCB")) {
					api.sendVideo(chatId, Constant.DELETE_GROUP_MEDIA_ID, "How to delete a group or channel.");
				}

				// Stickers
				if (chatMenuCallback.getButtonCallback().equals("UseStickersCB")) {
					api.sendVideo(chatId, Constant.USE_STICKERS_MEDIA_ID, "How to use and send stickers");
				}

				if (chatMenuCallback.getButtonCallback().equals("DownloadStickersCB")) {
					api.sendVideo(chatId, Constant.DOWNLOAD_STICKERS_MEDIA_ID, "How to download a sticker package.");

				}

				// Recall
				if (chatMenuCallback.getButtonCallback().equals("RecallMessageCB")) {
					api.sendVideo(chatId, Constant.RECALL_MSG_MEDIA_ID, "How to recall a message");
				}

				// ShareLocation
				if (chatMenuCallback.getButtonCallback().equals("ShareLocationCB")) {
					api.sendVideo(chatId, Constant.SHARE_LOCATION_MEDIA_ID, "How to share your location.");
				}
			}

			@Override
			public void onChatAdministrators(ChatAdministrators chatAdministrators) {
			}

			@Override
			public void onChatMember(ChatMember chatMember) {
			}

			private void sendBotMenuWithNavigationButton(String chatId) {
				Utils utils = new Utils();
				utils.setNavigationButton(chatId, MAIN_MENU_REF, api);
				MenuHelper utility = new MenuHelper();
				api.send(utility.createMainMenuMessage(chatId));
			}

			@Override
			public void onMyProfile(Profile user) {
				
			}

			@Override
			public void onUser(Profile user) {
			}

			@Override
			public void onUserJoinedBot(Profile user) {
				sendBotMenuWithNavigationButton(user.getUser().getId());
			}

			@Override
			public void userLeftBot(Profile user) {
				
			}

			@Override
			public void userStartedBot(Profile user) {
				sendBotMenuWithNavigationButton(user.getUser().getId());				
			}

			@Override
			public void userStoppedBot(Profile user) {
			
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
