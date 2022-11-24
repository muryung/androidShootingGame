package com.example.androidshootinggame;

import java.util.ArrayList;

public class ContectObject {
    public static boolean isAttacked = false;

    public void enemyContectBullet(ArrayList<Enemy> enemys, ArrayList<Bullet> bullets, Player player)
    {
        if (enemys != null && bullets != null)
        {
            Bullet tmpBullet = null;
            for (int i = 0; i < bullets.size(); i++)
            {
                tmpBullet = bullets.get(i);
                for (int j = 0; j < enemys.size(); j++) {
                    if( (tmpBullet.bulletY + tmpBullet.bulletHeight > enemys.get(j).enemyY) && (tmpBullet.bulletY < enemys.get(j).enemyY + enemys.get(j).enemyHeight) ) {
                        if ((tmpBullet.bulletX + tmpBullet.bulletWidth > enemys.get(j).enemyX) && (tmpBullet.bulletX < enemys.get(j).enemyX + enemys.get(j).enemyHeight)) {
                            bullets.remove(i);
                            enemyIsAttacked(enemys, player ,j);

                        }
                    }

                }
            }

        }
    }

    private boolean enemyIsAttacked(ArrayList<Enemy> enemys, Player player, int j)
    {
        enemys.get(j).enemyCurrentHp -= player.playerAttack;    // 적의 현재 HP는 플레이어의 공격력 만큼 줄어듬
        if(enemys.get(j).enemyCurrentHp <= 0)                    // 공격 받았음에도 적이 살아있으면 적 HP를 줄어든 HP로 설정
            enemys.remove(j);

        return true;
    }
}
