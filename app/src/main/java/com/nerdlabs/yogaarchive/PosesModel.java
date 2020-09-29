package com.nerdlabs.yogaarchive;

/**
 * Created by nikun on 26-07-2017.
 */

public class PosesModel {
    public String getPoseName() {
        return poseName;
    }

    public void setPoseName(String poseName) {
        this.poseName = poseName;
    }

    public String getPoseSubName() {
        return poseSubName;
    }

    public void setPoseSubName(String poseSubName) {
        this.poseSubName = poseSubName;
    }

    //public String getPoseDescriptionShort() {
        //return poseDescriptionShort;
    //}

    //public void setPoseDescriptionShort(String poseDescriptionShort) {
        //this.poseDescriptionShort = poseDescriptionShort;
    //}

    //public String getPoseBenefits() {
        //return poseBenefits;
    //}

    //public void setPoseBenefits(String poseBenefits) {
        //this.poseBenefits = poseBenefits;
    //}

    public String poseName;
    public String poseSubName;
    //public String poseDescriptionShort;
    //public String poseBenefits;

    public int getPoseimg_res() {
        return poseimg_res;
    }

    public void setPoseimg_res(int poseimg_res) {
        this.poseimg_res = poseimg_res;
    }

    public int poseimg_res;


    public PosesModel(String poseName, String poseSubName, int poseimg_res){
        this.setPoseName(poseName);
        this.setPoseSubName(poseSubName);
        this.setPoseimg_res(poseimg_res);

    }

}
