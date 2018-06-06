# Tetris

have rotateRight and rotateLeft in Shape class

    int[] origin = {0,0};
    int[] p1 = {1,1};
    
    int[] c = {0 + p1[1],0 - p1[0]};
    int[] cc = {0 - p1[1],0 + p1[0]};
    
    System.out.println("Clockwise - x: " + c[0] + " y: " + c[1]);
    System.out.println("CounterClockwise - x: " + cc[0] + " y: " + cc[1]);
