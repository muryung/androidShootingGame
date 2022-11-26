package com.example.androidshootinggame;

import java.util.ArrayList;

public class ContectObject {

    public void enemyContectBullet(Enemy enemys, ArrayList<Bullet> bullets, Player player)
    {
        if (enemys != null && bullets != null)
        {
            Bullet tmpBullet = null;
            for (int i = 0; i < bullets.size(); i++)
            {
                tmpBullet = bullets.get(i);
                for (int j = 0; j < enemys.enemyArray.size(); j++) {
                    if( (tmpBullet.bulletY + tmpBullet.bulletHeight > enemys.enemyArray.get(j).enemyY) && (tmpBullet.bulletY < enemys.enemyArray.get(j).enemyY + enemys.enemyArray.get(j).enemyHeight) ) {
                        if ((tmpBullet.bulletX + tmpBullet.bulletWidth > enemys.enemyArray.get(j).enemyX) && (tmpBullet.bulletX < enemys.enemyArray.get(j).enemyX + enemys.enemyArray.get(j).enemyHeight)) {
                            bullets.remove(i);
                            enemyIsAttacked(enemys, player ,j);


                        }
                    }

                }
            }

        }
    }

    private void enemyIsAttacked(Enemy enemys, Player player, int j)
    {
        enemys.enemyArray.get(j).enemyCurrentHp -= player.playerAttack;    // 적의 현재 HP는 플레이어의 공격력 만큼 줄어듬

        if (enemys.enemyArray.get(j).enemyCurrentHp > 0)
            enemys.enemyArray.get(j).setHpBar(enemys.enemyArray.get(j));
        else if(enemys.enemyArray.get(j).enemyCurrentHp <= 0)                    // 공격 받았음에도 적이 살아있으면 적 HP를 줄어든 HP로 설정
            enemys.enemyArray.remove(j);
    }
}
