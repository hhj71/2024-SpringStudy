package com.sist.chat;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/chat-ws")
public class ChatServer {
	// ����� ���� ����
	// synchronizedList : ����ȭ
	private static List<Session> users = Collections.synchronizedList(new ArrayList<Session>());
	// ���ӽø��� Session => ��ȣ
	// 1. ���ӽÿ� ó��
		@OnOpen
	// 2. �޼��� ���۽� ó��
		public void onOpen(Session session)
		{
			users.add(session); // ������ ����
			System.out.println("Ŭ���̾�Ʈ ����:"+session.getId());
		}
		@OnMessage
		public void onMessage(String message, Session session)
		{
			System.out.println(session.getId()+"���� �޽���"+message);
			for(Session s:users)
			{
				s.getBasicRemote().sendText(message);
			}
		}
	// 3. ����ÿ� ó�� 
		@OnClose
		public void onClose(Session session)
		{
			users.remove(session);
			System.out.println("Ŭ���̾�Ʈ ����:"+session.getId());
		}
}
