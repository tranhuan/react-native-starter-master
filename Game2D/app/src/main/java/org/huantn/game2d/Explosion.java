package org.huantn.game2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/*
Đôi khi bạn cần phải xử lý một vài hiệu ứng cho trò chơi,
    chẳng hạn bạn đang điều khiển một cái máy bay,
    khi nó rơi xuống đất máy bay phát nổ, vậy nổ là một hiệu ứng.
Trong phần này tôi sẽ mô phỏng khi bạn chạm (click) vào nhân vật Chibi, nó sẽ phát nổ.
Lớp Explosion mô phỏng một vụ nổ, khi bạn click vào nhân vật Chibi,
    nó bị loại ra khỏi trò chơi và một đối tượng Explosion được thêm vào trò chơi tại vị trí của nhân vật
    Chibi vừa bị loại bỏ.
 */
public class Explosion extends GameObject{
    private int rowIndex = 0 ;
    private int colIndex = -1 ;

    private boolean finish= false;
    private GameSurface gameSurface;

    public Explosion(GameSurface GameSurface, Bitmap image, int x, int y) {
        super(image, 5, 5, x, y);

        this.gameSurface= GameSurface;
    }

    public void update()  {
        this.colIndex++;

        // Play sound explosion.wav.
        if(this.colIndex==0 && this.rowIndex==0) {
            this.gameSurface.playSoundExplosion();
        }

        if(this.colIndex >= this.colCount)  {
            this.colIndex =0;
            this.rowIndex++;

            if(this.rowIndex>= this.rowCount)  {
                this.finish= true;
            }
        }
    }

    public void draw(Canvas canvas)  {
        if(!finish)  {
            Bitmap bitmap= this.createSubImageAt(rowIndex,colIndex);
            canvas.drawBitmap(bitmap, this.x, this.y,null);
        }
    }

    public boolean isFinish() {
        return finish;
    }
}
