package mission2;

import mission2.components.CarBreak;
import mission2.components.CarType;
import mission2.components.Engine;
import mission2.components.Steering;

public class Car {

    private CarType carType;
    private CarBreak carBreak;
    private Engine engine;
    private Steering steering;

    public Car(){
    }

    public void run(){
        if (!this.isValidCar()){
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (this.engine.equals(Engine.FAIL)) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }
        System.out.printf("Car Type : %s\n", this.carType);
        System.out.printf("Engine   : %s\n", this.engine);
        System.out.printf("Brake    : %s\n", this.carBreak);
        System.out.printf("Steering : %s\n", this.steering);
        System.out.println("자동차가 동작됩니다.");
    }

    public void testRun() {
        if (this.carType.equals(CarType.SEDAN) && this.carBreak.equals(CarBreak.CONTINENTAL)) {
            this.fail("Sedan에는 Continental제동장치 사용 불가");
            return;
        }
        if (this.carType.equals(CarType.SUV) && this.engine.equals(Engine.TOYOTA)) {
            this.fail("SUV에는 TOYOTA엔진 사용 불가");
            return;
        }
        if (this.carType.equals(CarType.TRUCK) && this.engine.equals(Engine.WIA)) {
            this.fail("Truck에는 WIA엔진 사용 불가");
            return;
        }
        if (this.carType.equals(CarType.TRUCK) && this.carBreak.equals(CarBreak.MANDO)) {
            this.fail("Truck에는 Mando제동장치 사용 불가");
            return;
        }
        if (this.carBreak.equals(CarBreak.BOSCH) && !this.steering.equals(Steering.BOSCH)) {
            this.fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
            return;
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    public void setBreak(CarBreak carBreak){
        this.carBreak = carBreak;
    }

    public void setEngine(Engine engine){
        this.engine = engine;
    }
    
    public void setSteering(Steering steering){
        this.steering = steering;
    }

    public void setCarType(CarType type){
        this.carType = type;
    }

    private boolean isValidCar() {
        if (this.carType.equals(CarType.SEDAN) && this.carBreak.equals(CarBreak.CONTINENTAL)) return false;
        if (this.carType.equals(CarType.SUV)   && this.engine.equals(Engine.TOYOTA))       return false;
        if (this.carType.equals(CarType.TRUCK) && this.engine.equals(Engine.WIA))          return false;
        if (this.carType.equals(CarType.TRUCK) && this.carBreak.equals(CarBreak.MANDO))  return false;
        if (this.carBreak.equals(CarBreak.BOSCH) && !this.steering.equals(Steering.BOSCH)) return false;
        return true;
    }

    private void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }
}
