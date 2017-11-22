package utils;

import constant.Constant;
import entity.*;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.awt.*;


/**
 * @description 刚体生成类（所有数据均为现实世界参数，内部自行转化）
 * @author Jack Chen
 * @date 2017/11/21
 */
public class Box2DUtil {

    /**
     * 创建正方形
     * @param x 物体横坐标
     * @param y 物体纵坐标
     * @param size 正方形边长
     * @param isStatic 是否固定
     * @param world 所属模拟世界
     * @param color 颜色
     */
    public static SquareBody createSquare(float x,float y, float size, boolean isStatic, World world, Color color) {
         //自定义形状
        PolygonShape polygon =new PolygonShape();
        float r = size / 2.0f / Constant.RATE;
        polygon.setAsBox(r, r);
        //配置物体属性参数
        FixtureDef fDef=new FixtureDef();
        if(isStatic) {
            fDef.density=0;
        } else {
            fDef.density=1;
        }
        //摩擦因子
        fDef.friction=1.0f;
        //恢复系数
        fDef.restitution=1.0f;
        fDef.shape=polygon;

        //创建刚体
        BodyDef bodyDef=new BodyDef();
        bodyDef.type=isStatic? BodyType.STATIC:BodyType.DYNAMIC;
        bodyDef.position.set(x+r,y+r);

        Body body=world.createBody(bodyDef);
        body.createFixture(fDef);
        return new SquareBody(body,size/Constant.RATE,color);
     }

    /**
     * 创建完全弹性碰撞正方形
     * @param x 物体横坐标
     * @param y 物体纵坐标
     * @param size 吸收器边长
     * @param world 所属模拟世界
     * @param color 颜色
     */
    public static AdvanceSquareBody createAdvanceSquareBody(float x,float y, float size, World world, Color color) {
        //自定义形状
        PolygonShape polygon =new PolygonShape();
        float r = size / 2.0f / Constant.RATE;
        polygon.setAsBox(r, r);
        //配置物体属性参数
        FixtureDef fDef=new FixtureDef();
        fDef.density=0;
        //摩擦因子
        fDef.friction=1.0f;
        //恢复系数
        fDef.restitution=1.0f;
        fDef.shape=polygon;

        //创建刚体
        BodyDef bodyDef=new BodyDef();
        bodyDef.type=BodyType.STATIC;
        bodyDef.position.set(x+r,y+r);

        Body body=world.createBody(bodyDef);
        body.createFixture(fDef);
        return new AdvanceSquareBody(body,size/Constant.RATE,color);
    }

    /**
     * 创建吸收器
     * @param x 物体横坐标
     * @param y 物体纵坐标
     * @param size 正方形边长
     * @param world 所属模拟世界
     * @param color 颜色
     */
    public static AbsorberBody createAbsorber(float x,float y, float size, World world, Color color) {
        //自定义形状
        PolygonShape polygon =new PolygonShape();
        float r = size / 2.0f / Constant.RATE;
        polygon.setAsBox(r, r);
        //配置物体属性参数
        FixtureDef fDef=new FixtureDef();
        fDef.density=0;
        //摩擦因子
        fDef.friction=1.0f;
        //恢复系数
        fDef.restitution=0.0f;
        fDef.shape=polygon;

        //创建刚体
        BodyDef bodyDef=new BodyDef();
        bodyDef.type=BodyType.STATIC;
        bodyDef.position.set(x+r,y+r);

        Body body=world.createBody(bodyDef);
        body.createFixture(fDef);
        return new AbsorberBody(body,size/Constant.RATE,color);
    }

    /**
     * 创建圆形
     * @param x 物体横坐标
     * @param y 物体中坐标
     * @param radius 物体半径
     * @param isStatic 是否固定
     * @param world 所属虚拟世界
     * @param color 物体颜色
     * @return
     */
    public static CircleBody createCircle(float x,float y, float radius, boolean isStatic, World world, Color color){
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = radius/ Constant.RATE;
        //配置物体属性参数
        FixtureDef fDef=new FixtureDef();
        if(isStatic) {
            fDef.density=0;
        } else {
            fDef.density=1;
        }
        //摩擦因子
        fDef.friction=1.0f;
        //恢复系数
        fDef.restitution=1.0f;
        fDef.shape = circleShape;

        //创建刚体
        BodyDef bodyDef=new BodyDef();
        bodyDef.type=isStatic? BodyType.STATIC:BodyType.DYNAMIC;
        bodyDef.position.set((x+radius)/Constant.RATE,(y+radius)/Constant.RATE);
        Body body=world.createBody(bodyDef);
        body.createFixture(fDef);
        return new CircleBody(body,radius/Constant.RATE,color);
    }

    /**
     * 创建等腰三角形
     * @param x 物体横坐标
     * @param y 物体中坐标
     * @param size 直角边长
     * @param isStatic 是否固定
     * @param world 所属虚拟世界
     * @param color 物体颜色
     * @return
     */
    public static TriangleBody createTriangle(float x, float y, float size, boolean isStatic, World world, Color color){
        PolygonShape triangleShape = new PolygonShape();

        float r = size / 2.0f / Constant.RATE;
        triangleShape.set(new Vec2[]{new Vec2(-r, -r), new Vec2(-r, r), new Vec2(r, -r)},3);
        //配置物体属性参数
        FixtureDef fDef=new FixtureDef();
        if(isStatic) {
            fDef.density=0;
        } else {
            fDef.density=1;
        }
        //摩擦因子
        fDef.friction=1.0f;
        //恢复系数
        fDef.restitution=1.0f;
        fDef.shape = triangleShape;

        //创建刚体
        BodyDef bodyDef=new BodyDef();
        bodyDef.type=isStatic? BodyType.STATIC:BodyType.DYNAMIC;
        bodyDef.position.set(x+r,y+r);
        Body body=world.createBody(bodyDef);
        body.createFixture(fDef);
        return new TriangleBody(body,size/Constant.RATE,color);
    }


    /**
     * 创建小球
     * @param x 小球横坐标（左上）
     * @param y 小球纵坐标（左上）
     * @param radius 半径
     * @param world 所属虚拟世界
     * @param color 物体颜色
     * @return
     */
    public static Ball createBall(float x, float y, float radius, World world, Color color){
        CircleShape ballShape = new CircleShape();

        float r = radius / Constant.RATE;
        ballShape.m_radius = r;
        //配置物体属性参数
        FixtureDef fDef=new FixtureDef();
        fDef.density=1;
        //摩擦因子
        fDef.friction=1.0f;
        //恢复系数
        fDef.restitution=1.0f;
        fDef.shape = ballShape;

        //创建刚体
        BodyDef bodyDef=new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(x+r,y+r);
        Body body=world.createBody(bodyDef);
        body.createFixture(fDef);
        return new Ball(body,r,color);
    }

    /**
     * 创建等腰梯形
     * @param x 物体横坐标
     * @param y 物体中坐标
     * @param size 直角边长
     * @param isStatic 是否固定
     * @param world 所属虚拟世界
     * @param color 物体颜色
     * @return
     */
    public static TrapezoidBody trapezoidBody(float x, float y, float size, boolean isStatic, World world, Color color){
        PolygonShape trapezoidBody = new PolygonShape();

        float r = size / 2.0f / Constant.RATE;
        trapezoidBody.set(new Vec2[]{new Vec2(-r, -r), new Vec2(-r/2.0f, r), new Vec2(r/2.0f, r), new Vec2(r, -r)},4);
        //配置物体属性参数
        FixtureDef fDef=new FixtureDef();
        if(isStatic) {
            fDef.density=0;
        } else {
            fDef.density=1;
        }
        //摩擦因子
        fDef.friction=1.0f;
        //恢复系数
        fDef.restitution=1.0f;
        fDef.shape = trapezoidBody;

        //创建刚体
        BodyDef bodyDef=new BodyDef();
        bodyDef.type=isStatic? BodyType.STATIC:BodyType.DYNAMIC;
        bodyDef.position.set(x+r,y+r);
        Body body=world.createBody(bodyDef);
        body.createFixture(fDef);
        return new TrapezoidBody(body,size/Constant.RATE,color);
    }




}
