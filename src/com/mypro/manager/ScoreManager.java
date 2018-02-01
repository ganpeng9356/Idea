package com.mypro.manager;

import java.util.ArrayList;
import java.util.HashMap;

import com.mypro.base.graphics.Bitmap;
import com.mypro.constant.Constant;
//import com.mypro.model.FishGold;
import com.mypro.model.GamingInfo;
//import com.mypro.model.Gold;
//import com.mypro.model.GoldParticleEffect;
import com.mypro.model.FishGold;
import com.mypro.model.HighPoint;
import com.mypro.model.HundredPoint;
import com.mypro.tools.LogTools;

/**
 * �÷ֹ�����
 * @author Leslie Leung
 *
 */
public class ScoreManager {
	private static ScoreManager scoreManager;
	/**
	 * ��Ҷ�Ӧ�Ľ�����ͼƬ
	 * ������ԱȽ����⣬�ڴ��ڹ�������ʼ���ģ����ﵱ��Ƿ������
	 */
//	private Bitmap[] goldNum;
	/**
	 * �߷ֵ����ͼƬ
	 * key:���� value:��Ӧ��ͼƬ
	 */
	private HashMap<Integer,Bitmap[]> highPoint = new HashMap<Integer,Bitmap[]>();
	/**
	 * �ٷֵ����ͼƬ
	 * key:���� value:��Ӧ��ͼƬ
	 */
	private HashMap<Integer,Bitmap[]> hundredPoint = new HashMap<Integer,Bitmap[]>();
	private ScoreManager(){}
	public static ScoreManager getScoreManager(){
		if(scoreManager==null){
			scoreManager = new ScoreManager();
		}
		return scoreManager;
	}
	/**
	 * �ӷֲ���
	 * @param value
	 * @param showX		��ʾλ�õ�x����
	 * @param showY		��ʾλ�õ�Y����
	 */
	public void addScore(int value,final float showX,final float showY){
		GamingInfo.getGamingInfo().setScore(GamingInfo.getGamingInfo().getScore()+value);
		//��ͬ�ķ����в�ͬ����ʾЧ��
		switch(value){
			case 40:
				showHighPoint(40,showX,showY);
				break;
			case 50:
				showHighPoint(50,showX,showY);
				break;
			case 60:
				showHighPoint(60,showX,showY);
				break;
			case 70:
				showHighPoint(70,showX,showY);
				break;
			case 80:
				showHighPoint(80,showX,showY);
				break;
			case 90:
				showHighPoint(90,showX,showY);
				break;
			case 100:
				showHundredPoint(100,showX,showY);
				break;
			case 120:
				showHundredPoint(120,showX,showY);
				break;
			case 150:
				showHundredPoint(150,showX,showY);
				break;
			default:
				showGoldNum(value,showX,showY);
		}
	}
	/**
	 * ��ʾ��õĽ����
	 * @param score
	 * @param goldFromX
	 * @param goldFromY
	 */
	private void showGoldNum(final int score,final float goldFromX,final float goldFromY){
		//��ʾ�õ��ķ���
		new Thread(new Runnable() {					
			@Override
			public void run() {
				try {
					FishGold fg = new FishGold(score);
//					GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.GOLD_LAYER, fg);
					Thread.sleep(1000);
//					GamingInfo.getGamingInfo().getSurface().removeDrawablePic(Constant.GOLD_LAYER, fg);
				} catch (Exception e) {
					e.printStackTrace();
					LogTools.doLogForException(e);
				}
				
			}
		}).start();
	}
	/**
	 * ��ʾ�߷���
	 * 40-90�ֵ�����
	 * @param score
	 * @param goldFromX
	 * @param goldFromY
	 */
	private void showHighPoint(final int score,final float goldFromX,final float goldFromY){
		new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HighPoint hp = new HighPoint(highPoint.get(score));
				hp.getPicMatrix().setTranslate(goldFromX, goldFromY);
				GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.HIGH_POINT_LAYER, hp);
//				SoundManager.playSound(SoundManager.SOUND_BGM_HIGH_POINT);
				try {
					int showTime = 0;//��ʾ5��ѭ��
					int currentId = 0;
					while(GamingInfo.getGamingInfo().isGaming()){
						while(!GamingInfo.getGamingInfo().isPause()){
							hp.setCurrentPicId(currentId);
							currentId++;
							if(currentId==hp.getActPicLength()){
								currentId=0;
								showTime++;
							}
							if(showTime==5){		
								break;
							}
							Thread.sleep(100);
						}
						break;
					}
				} catch (Exception e) {
					LogTools.doLogForException(e);
				}
				GamingInfo.getGamingInfo().getSurface().removeDrawablePic(Constant.HIGH_POINT_LAYER, hp);
			}
		}).start();
	}
	/**
	 * ��ʾ�߷���
	 * 40-90�ֵ�����
	 * @param score
	 * @param goldFromX
	 * @param goldFromY
	 */
	private void showHundredPoint(final int score,final float goldFromX,final float goldFromY){
		new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HundredPoint hp = new HundredPoint(hundredPoint.get(score));
				hp.getPicMatrix().setTranslate(goldFromX, goldFromY);
				GamingInfo.getGamingInfo().getSurface().putDrawablePic(Constant.HUNDRED_POINT_LAYER, hp);
//				SoundManager.playSound(SoundManager.SOUND_BGM_HUNDRED_POINT);
				try {
					int showTime = 0;//��ʾ5��ѭ��
					int currentId = 0;
					while(GamingInfo.getGamingInfo().isGaming()){
						while(!GamingInfo.getGamingInfo().isPause()){
							hp.setCurrentPicId(currentId);
							currentId++;
							if(currentId==hp.getActPicLength()){
								currentId=0;
								showTime++;
							}
							if(showTime==5){		
								break;
							}
							Thread.sleep(100);
						}
						break;
					}
				} catch (Exception e) {
					LogTools.doLogForException(e);
				}
				GamingInfo.getGamingInfo().getSurface().removeDrawablePic(Constant.HUNDRED_POINT_LAYER, hp);
			}
		}).start();
	}
	/**
	 * ���ٲ���
	 */
	public static void destroy(){
		scoreManager = null;
	}
	/**
	 * ��ʼ������
	 */
	public void init(){
		try {
			
			//��ʼ�����
//			initGold(ImageManager.getImageMnagaer().getImagesMapByImageConfig(ImageManager.getImageMnagaer().createImageConfigByPlist("score/goldItem"),ImageManager.getImageMnagaer().scaleNum));
			//��ʼ���߷�
			initHighPoint(ImageManager.getImageMnagaer().getImagesMapByImageConfig(ImageManager.getImageMnagaer().createImageConfigByPlist("score/highPoint"),ImageManager.getImageMnagaer().scaleNum));
			//��ʼ���ٷ�
			initHundredPoint(ImageManager.getImageMnagaer().getImagesMapByImageConfig(ImageManager.getImageMnagaer().createImageConfigByPlist("score/hundred"),ImageManager.getImageMnagaer().scaleNum));
		} catch (Exception e) {
			LogTools.doLogForException(e);
		}
	}
	/**
	 * ��ʼ���߷�
	 * @param imgs
	 */
	private void initHighPoint(HashMap<String,Bitmap> highPoint){
		//Ч��ͼȫ��(40_1.png)
		StringBuffer highPointFullName = new StringBuffer();
		//�������ֱ��
		int highPointBaseNum = 40;
		int highPointNum;
		ArrayList<Bitmap> allHighPointList = new ArrayList<Bitmap>();
		while(GamingInfo.getGamingInfo().isGaming()){
			highPointNum = 1;
			while(GamingInfo.getGamingInfo().isGaming()){
				highPointFullName.delete(0, highPointFullName.length());
				highPointFullName.append(highPointBaseNum+"_"+highPointNum+".png");
				Bitmap highPointImg = highPoint.get(highPointFullName.toString());
				if(highPointImg==null){
					break;
				}
				highPointNum++;
				allHighPointList.add(highPointImg);
			}
			if(allHighPointList.size()==0){
				break;
			}
			//������ת��Ϊ����
			Bitmap[] highPointArr = new Bitmap[allHighPointList.size()];
			for(int i =0;i<allHighPointList.size();i++){
				highPointArr[i] = allHighPointList.get(i);
			}	
			allHighPointList.clear();
			this.highPoint.put(highPointBaseNum, highPointArr);
			highPointBaseNum+=10;
		}
		
	}
	/**
	 * ��ʼ���ٷ�
	 * @param imgs
	 */
	private void initHundredPoint(HashMap<String,Bitmap> hundredPoint){
		//Ч��ͼȫ��(40_1.png)
		StringBuffer hundredPointFullName = new StringBuffer();
		//�������ֱ��
		int hundredPointBaseNum = 100;
		int hundredPointNum;
		ArrayList<Bitmap> allHundredPointList = new ArrayList<Bitmap>();
		while(GamingInfo.getGamingInfo().isGaming()){
			hundredPointNum = 1;
			while(GamingInfo.getGamingInfo().isGaming()){
				hundredPointFullName.delete(0, hundredPointFullName.length());
				hundredPointFullName.append(hundredPointBaseNum+"_"+hundredPointNum+".png");
				Bitmap hundredPointImg = hundredPoint.get(hundredPointFullName.toString());
				if(hundredPointImg==null){
					break;
				}
				hundredPointNum++;
				allHundredPointList.add(hundredPointImg);
			}
			if(allHundredPointList.size()>0){
				//������ת��Ϊ����
				Bitmap[] hundredPointArr = new Bitmap[allHundredPointList.size()];
				for(int i =0;i<allHundredPointList.size();i++){
					hundredPointArr[i] = allHundredPointList.get(i);
				}
				allHundredPointList.clear();
				this.hundredPoint.put(hundredPointBaseNum, hundredPointArr);
			}			
			hundredPointBaseNum+=10;
			if(hundredPointBaseNum>=150){
				break;
			}
		}
		
	}
	/**
	 * ���ý�Ҷ�Ӧ������ͼƬ
	 * @param goldNum
	 */
//	public void setGoldNum(Bitmap[] goldNum) {
//		this.goldNum = goldNum;
//	}
	
}
