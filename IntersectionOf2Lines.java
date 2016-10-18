package javaapplication20;

import java.util.Scanner;

class point {

    double x_coord;
    double y_coord;
    

    public point(double begin_point, double end_point) {
        this.x_coord = begin_point;
        this.y_coord = end_point;
        
    }

}

class line {

    point point_1;
    point point_2;

    public line(point point_1, point point_2) {
        this.point_1 = point_1;
        this.point_2 = point_2;
    }

    public double slope() {
      double num= this.point_2.y_coord-this.point_1.y_coord;
      double dem= this.point_2.x_coord-this.point_1.x_coord;
      double slope = num/dem;
      return slope;
    }
    
    public double getC()
    {
        double x=this.point_1.x_coord;
        double y=this.point_1.y_coord;
        double slope=this.slope();
        double c =y-(x*slope);
        return c;
    }
    
    
    public boolean parallel(line l1 , line l2)
    {
        return this.slope()==l2.slope();
    }
    

    public double getX(line l2)
    {
      double slope1=this.slope();
      double c1=this.getC();
      
      double c2=l2.getC();
      double slope2=l2.slope();
      
      c2*=-1;
      slope2*=-1;
      
      double slope_final=slope1+slope2;
      double c=c1+c2;
      
      double x = c/(-1*slope_final);
      
      return x;
    }
    
    public double getY(line l2)
    {
        double x = this.getX(l2);
        double y = (this.slope()*x)+this.getC();
        return y;
    }
    public point intersectionPoint(line l2)
    {
        double x = this.getX(l2);
        double y= this.getY(l2);
        point intersection = new point(x,y);
        return intersection;
    }
    
    
    
    
    
    
   
    public boolean vertical()
    {
        if(this.point_1.x_coord==this.point_2.x_coord) 
            return true;
        return false;
    }
    
    public boolean intersect_vertical(line l2)
    {
         double x_L1=this.point_1.x_coord;
            
            double x1_L2=l2.point_1.x_coord;
            double x2_L2=l2.point_2.x_coord;
            
            if (((x1_L2<x_L1)&&(x2_L2>x_L1))||((x1_L2>x_L1)&&(x2_L2<x_L1)))
            {
                 double y1_L1=this.point_1.y_coord;
                 double y2_L1=this.point_2.y_coord;
            
            double y1_L2=l2.point_1.y_coord;
            double y2_L2=l2.point_2.y_coord;
            
           boolean c1=y1_L2<y1_L1;
           boolean c2=y1_L2<y2_L1;
           boolean c3=y1_L2>y1_L1;
           boolean c4=y1_L2>y2_L1;
           
           
           boolean c5=y2_L2<y1_L1;
           boolean c6=y2_L2<y2_L1;
           boolean c7=y2_L2>y1_L1;
           boolean c8=y2_L2>y2_L1;
           
             return !((c1&&c2&&c5&&c6)||((c3&&c4&&c7&&c8)));
    }
            return false;
    }
    
    public boolean checkIntersection(line l2)
    {
        if (this.parallel(this, l2))return false ;
        if (this.vertical())
        {
            return intersect_vertical(l2);
           
        }
        else if (l2.vertical())
        {
          
            return l2.intersect_vertical(this);
        }
        else 
        {
                
      
        double x_l1_p1=this.point_1.x_coord;
        double y_l1_p1=this.point_1.y_coord;
        
        
        double x_l1_p2=this.point_2.x_coord;
        double y_l1_p2=this.point_2.y_coord;
        
        
        double x_l2_p1=l2.point_1.x_coord;
        double y_l2_p1=l2.point_1.y_coord;
        
        
        double x_l2_p2=l2.point_2.x_coord;
        double y_l2_p2=l2.point_2.y_coord;
        
        point intersection_point=this.intersectionPoint(l2);
        double intersection_x=intersection_point.x_coord;
        double intersection_y=intersection_point.y_coord;
        
        boolean cond1=((intersection_x>x_l1_p1)&&(intersection_x>x_l1_p2))||((intersection_x<x_l1_p1)&&(intersection_x<x_l1_p2));
        boolean cond2=((intersection_x>x_l2_p1)&&(intersection_x>x_l2_p2))||((intersection_x<x_l2_p1)&&(intersection_x<x_l2_p2));
        
        
        boolean cond3=((intersection_y>y_l1_p1)&&(intersection_y>y_l1_p2))||((intersection_y<y_l1_p1)&&(intersection_y<y_l1_p2));
        boolean cond4=((intersection_y>y_l2_p1)&&(intersection_y>y_l2_p2))||((intersection_y<y_l2_p1)&&(intersection_y<y_l2_p2));
        
        return !(cond1||cond2||cond3||cond4);
    } 
    }
    
}



public class JavaApplication20 {

    public static void main(String[] args) {
        
        Scanner reader=new Scanner(System.in);
        System.out.println("Please enter  first end point coordinates of your first line");
        double x1 = reader.nextDouble();
        double y1= reader.nextDouble();
        point point1_L1=new point(x1,y1);
        
        System.out.println("Please enter  Second end point coordinates of your first line");
        double x2 = reader.nextDouble();
        double y2= reader.nextDouble();
        point point2_L1=new point(x2,y2);
        
        line line1=new line(point1_L1,point2_L1);
        
        System.out.println("Please enter  first end point coordinates of your Second line");
        double x11 = reader.nextDouble();
        double y11= reader.nextDouble();
        point point1_L2=new point(x11,y11);
        
        System.out.println("Please enter  Second end point coordinates of your Second line");
        double x22 = reader.nextDouble();
        double y22= reader.nextDouble();
        point point2_L2=new point(x22,y22);
        
        
        line line2=new line(point1_L2,point2_L2);
        boolean intersects = line1.checkIntersection(line2);
        
        if (intersects)
        {
            System.out.println("Your lines intersect");
        }
        else 
        {
            
            System.out.println("Your lines don't intersect");
        }
    }

}
