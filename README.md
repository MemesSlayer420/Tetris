# Tetris

have rotateRight and rotateLeft in Shape class

    With origin of 0,0 only:
    
    int[] origin = {0,0};
    int[] p1 = {1,1};
    
    int[] c = {0 + p1[1],0 - p1[0]};
    int[] cc = {0 - p1[1],0 + p1[0]};
    
    System.out.println("Clockwise - x: " + c[0] + " y: " + c[1]);
    System.out.println("CounterClockwise - x: " + cc[0] + " y: " + cc[1]);
    
    
    With any origin:
    
    int[] origin = {2,1};
    int[] p1 = {7,11};
    
    int[] c = {origin[0] - (p1[1] - origin[1]),origin[1] + (p1[0] - origin[0])};
    int[] cc = {origin[0] + (p1[1] - origin[1]), origin[1] - (p1[0] - origin[0])};

    //clockwise:
    System.out.println("Clockwise - x: " + c[0] + " y: " + c[1]);
    System.out.println("CounterClockwise - x: " + cc[0] + " y: " + cc[1]);
