package com.example.androidshootinggame.InGame;

import com.example.androidshootinggame.MainActivity;

import java.util.ArrayList;

public class ContactObject {
    public static int enemyCount = 0;

    public void enemyContactBullet(Enemy enemys, ArrayList<Bullet> bullets, Player player)
    {
        if (enemys != null && bullets != null)
        {
            Bullet tmpBullet = null;
            for (int i = 0; i < bullets.size(); i++)
            {
                tmpBullet = bullets.get(i);
                for (int j = 0; j < enemys.enemyArray.size(); j++) {
                    if( (tmpBullet.bulletY + tmpBullet.bulletHeight > enemys.enemyArray.get(j).enemyY) &&
                            (tmpBullet.bulletY < enemys.enemyArray.get(j).enemyY + enemys.enemyArray.get(j).enemyHeight) ) {
                        if ((tmpBullet.bulletX + tmpBullet.bulletWidth > enemys.enemyArray.get(j).enemyX) &&
                                (tmpBullet.bulletX < enemys.enemyArray.get(j).enemyX + enemys.enemyArray.get(j).enemyWidth)) {
                            bullets.remove(i);
                            isEnemyAttacked(enemys, player ,j);

                        }
                    }
                }
            }

        }
    }

    private void isEnemyAttacked(Enemy enemys, Player player, int j)
    {
        enemys.enemyArray.get(j).enemyCurrentHp -= player.playerAttack;      // 적의 현재 HP는 플레이어의 공격력 만큼 줄어듬

        if (enemys.enemyArray.get(j).enemyCurrentHp > 0)                            // 적이 공격을 받아도 살아있으면 체력바만 줄어들어듬
            enemys.enemyArray.get(j).setHpBar(enemys.enemyArray.get(j));
        else if(enemys.enemyArray.get(j).enemyCurrentHp <= 0)
        {
            enemys.enemyArray.remove(j);
            enemyCount++;
        }
    }

    public void enemyContactPlayer(Enemy enemys, Player player, PlayerHeart playerHeart)
    {
        for (int j = 0; j < enemys.enemyArray.size(); j++) {
            if( (player.playerY + player.playerHeight + 200 > enemys.enemyArray.get(j).enemyY) &&
                    (player.playerY < enemys.enemyArray.get(j).enemyY + enemys.enemyArray.get(j).enemyHeight) ) {

                if ((player.playerX + player.playerWidth + 200 > enemys.enemyArray.get(j).enemyX) &&
                        (player.playerX < enemys.enemyArray.get(j).enemyX + enemys.enemyArray.get(j).enemyWidth)) {

                    reducePlayerHeart(playerHeart);
                    enemys.enemyArray.remove(j);
                }
            }
        }
    }

    private void reducePlayerHeart(PlayerHeart playerHeart)
    {
        if (playerHeart.heartImgs.size() > 0)
            playerHeart.heartImgs.remove(playerHeart.heartImgs.size() - 1);
    }
}
