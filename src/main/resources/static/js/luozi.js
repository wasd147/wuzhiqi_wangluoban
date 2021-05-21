function luozi(x, y) {
    var row, col, index = 0;

    if (x % 40 < 20) {
        col = parseInt(x / 40);
    } else {
        col = parseInt(x / 40) + 1;
    }
    row = y % 40 < 20 ? parseInt(y / 40) : parseInt(y / 40) + 1;
    // alert(row+"行"+col+"行");  //第几列行第几列

    if (maps[row][col] === 0) {
        if (isBlack) {
            ctx.drawImage(black, col * 40, row * 40); //下黑子
            isBlack = false;
            //++++++++++++++++++

            //+++++++++++++++++++++++
            maps[row][col] = 2; //黑子为2
            iswin(2, row, col);

        } else {
            ctx.drawImage(white, col * 40, row * 40);
            isBlack = true;
            //++++++++++++++++++

            //+++++++++++++++++++++++
            maps[row][col] = 1; //白子为1
            iswin(1, row, col);

        }
    }

    function iswin(t, row, col) {
        var orgrow, orgcol, total;
        reset();
        // alert(total);

        //判断每行是否有五个
        while (col > 0 && maps[row][col - 1] == t) { //当前子左边还有
            total++;
            col--;

        }
        ;
        row = orgrow;
        col = orgcol;
        while (col + 1 < 16 && maps[row][col + 1] == t) { //当前子右边还有
            col++;
            total++;
        }
        ;
        // alert(total);
        celebrate();

        //判断每列是否有五个
        reset();

        while (row > 0 && maps[row - 1][col] == t) { //当前子上面还有
            total++;
            row--;
        }
        row = orgrow;
        col = orgcol;
        while (row + 1 < 16 && maps[row + 1][col] == t) { //下面
            total++;
            row++;
        }
        celebrate();

        //左上 右下有没有五个
        reset();
        while (row > 0 && col > 0 && maps[row - 1][col - 1] == t) { //左上1
            row--;
            col--;
            total++;
        }
        row = orgrow;
        col = orgcol;
        while (row + 1 < 16 && col + 1 < 16 && maps[row + 1][col + 1] == t) { //右下1
            row++;
            col++;
            total++;
        }
        // alert(total)
        celebrate();

        //左下 右上有没有五个
        reset();
        // alert(total);
        while (row > 0 && col + 1 < 16 && maps[row - 1][col + 1] == t) { //右上
            row--;
            col++;
            total++;
        }
        row = orgrow;
        col = orgcol;
        while (row + 1 < 16 && col > 0 && maps[row + 1][col - 1] == t) { //左下
            row++;
            col--;
            total++;
        }
        // alert(total);
        celebrate();

        function celebrate() { //显示哪边赢
            if (total >= 5) {
                if (t == 1) {
                    alert("白子赢");
                    // text[0].innerHTML="白子赢";
                    // cxt1.clearRect(0,0,can1.width,can1.height);
                    ctx1.clearRect(0, 0, can1[0].width, can1[0].height);
                    ctx1.fillText("白子赢", 0, 100);
                } else {
                    alert("黑子赢");
                    // text[0].innerHTML="黑子赢";
                    // cxt1.clearRect(0,0,can1.width,can1.height);
                    ctx1.clearRect(0, 0, can1[0].width, can1[0].height);
                    ctx1.fillText("黑子赢", 0, 100);
                }
            }
        }

        function reset() {
            orgrow = row;
            orgcol = col;
            total = 1;
        }
    }

}