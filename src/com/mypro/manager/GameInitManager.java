package com.mypro.manager;

import com.mypro.constant.Constant;
import com.mypro.model.GamingInfo;

import com.mypro.tools.LogTools;


/**
 * ��Ϸ��ʼ��������
 * @author Leslie Leung
 *
 */
public class GameInitManager {
	private static GameInitManager manager;
	private boolean initing = true;
	/**
	 * �Ƿ����ڳ�ʼ��
	 * @return
	 */
	public boolean isIniting(){
		return initing;
	}
	private GameInitManager(){}
	public static GameInitManager getGameInitManager(){
		if(manager == null){
			manager = new GameInitManager();
		}
		return manager;
	}
	public void init(){
		ImageManager.getImageMnagaer().initManager();

		initGame();//��ʼ����Ϸ
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		beginGame();
		initing = false;
	}
	/**
	 * ��ʼ���������
	 */
	private void initComponents(){
		LayoutManager.getLayoutManager().init();
	}

	/**
	 * ��ʼ����Ϸ
	 */
	private void initGame(){
		//��ʼ���������
		this.initComponents();
		//��ʼ���÷ֹ�����
		ScoreManager.getScoreManager().init();

		//��ʼ�����ڹ�����
		CannonManager.getCannonManager().init();

		//��ʼ���������
		FishManager.getFishMananger().initFish();

		//��ʼ����Ⱥ������
		GamingInfo.getGamingInfo().setShoalManager(new ShoalManager());

		//��ʼ���ؿ�������
		GamePartManager.getManager().prepare();

		//��ʼ������
		CannonManager.getCannonManager().initCannon();

	}

	/**
	 * ֹͣ��Ϸ
	 */
	public void stop(){

		try {
			//������Ϸ����
			GamingInfo.getGamingInfo().setGaming(false);
			Thread.sleep(1000);

			//ע���������
			FishManager.destroy();
			//ע����Ϸ�ؿ�������
			GamePartManager.getManager().destroy();

			//ע���Լ�
			manager = null;
		} catch (Exception e) {
			LogTools.doLogForException(e);
		}
	}

	/**
	 * ��ʼ��Ϸ
	 */
	private void beginGame(){
		//��ʼ
		GamePartManager.getManager().start();
	}

}
