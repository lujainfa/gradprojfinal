package com.lujainalfalaileh.bloodbank;


public class Availability {


   private String cO11,cO22,cB11,cB22,cA11,cA22,cAB11,cAB22,cWholeBlood1,cPlatelet1,cRedCells1,bName;




    Availability(){}


  // public Availability(String cO11, String cO22,String cB11,String cB22,String cA11,String cA22,
                      //  String cAB11,String cAB22,String cWholeBlood1,String cPlatelet1,String cRedCells1) {

    //}


    public void setbName(String bName) {
        this.bName = bName;
    }
    public String getbName()
    {
        return bName;
    }
    public String getcO11() {
        return cO11;
    }
    public String getcO22() {
        return cO22;
    }
    public String getcB11() {return cB11;}
    public String getcB22() {
        return cB22;
    }
    public String getcAB11() {return cAB11;}
    public String getcAB22() {return cAB22;}
    public String getcA11() {return cA11;}
    public String getcA22() {
        return cA22;
    }

    public String getcWholeBlood1() {return cWholeBlood1;}
    public String getcPlatelet1() {return cPlatelet1;}
    public String getcRedCells1() {
        return cRedCells1;
    }

    public void  setcO11(String cO11){this.cO11=cO11;}
    public void  setcO22(String cO22){this.cO22=cO22;}
    public void  setcB11(String cB11){this.cB11=cB11;}
    public void  setcB22(String cB22){this.cB22=cB22;}
    public void  setcA11(String cA11){this.cA11=cA11;}
    public void  setcA22(String cA22){this.cA22=cA22;}
    public void  setcAB11(String cAB11){this.cAB11=cAB11;}
    public void  setcAB22(String cAB22){this.cAB22=cAB22;}

    public void  setWholeBlood1(String cWholeBlood1){this.cWholeBlood1=cWholeBlood1;}
    public void  setcPlatelet1(String cPlatelet1){this.cPlatelet1=cPlatelet1;}
    public void  setcRedCells1 (String cRedCells1){this.cRedCells1=cRedCells1;}




    public Availability(String cO11, String cO22,String cB11,String cB22,String cA11,String cA22,
                        String cAB11,String cAB22,String cWholeBlood1, String cPlatelet1,String cRedCells1,String bName)
    {
        this.cO11=cO11;
        this.cO22=cO22;
        this.cA11=cA11;
        this.cA22=cA22;
        this.cB11=cB11;
        this.cB22=cB22;
        this.cAB11=cAB11;
        this.cAB22=cAB22;
        this.cWholeBlood1=cWholeBlood1;
        this.cPlatelet1=cPlatelet1;
        this.cRedCells1=cRedCells1;
        this.bName=bName;


    }





}
