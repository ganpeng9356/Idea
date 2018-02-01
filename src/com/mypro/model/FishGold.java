package com.mypro.model;

import com.mypro.base.graphics.Bitmap;
import com.mypro.base.graphics.Canvas;
import com.mypro.base.graphics.Paint;
import com.mypro.tools.LogTools;

/**
 * ��ʾ��׽������õĽ������
 * @author Leslie Leung
 *
 */
public class FishGold extends DrawableAdapter{
	private int gold;//���ֵ��¼��ǰ���Ӧ��ʾ�Ľ����
	private int[] num_index = new int[1];//�������ֵ������������һ��Ԫ�ش���÷ֵ����λ������������

	public FishGold(int gold){
		try {
			this.gold = gold;
			updateNumIndex();
		} catch (Exception e) {
			e.printStackTrace();
			LogTools.doLogForException(e);
		}
	
	}
	@Override
	public void onDraw(Canvas canvas, Paint paint) {	
		
	}
	/**
	 * ������������
	 */
	private void updateNumIndex(){
		String num = gold+"";
		num_index = new int[num.length()];
		int index = 0;
		for(char n:num.toCharArray()){
			num_index[index] = n-48;
			index++;
		}		
	}	
	
	public Bitmap getCurrentPic() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPicWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPicHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
