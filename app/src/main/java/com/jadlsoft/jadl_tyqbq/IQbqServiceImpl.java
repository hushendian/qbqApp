package com.jadlsoft.jadl_tyqbq;


import android.util.Log;

public class IQbqServiceImpl extends IQbqAidlInterface.Stub {

    private static final String TAG = "QBGL";

    @Override
    public String getDwxx()  {
        AidlResult aidlResult = new AidlResult();
        Dwxx dwxx = new Dwxx();
        dwxx.setDwdm("单位代码");
        dwxx.setDwlx("单位类型");
        dwxx.setDwlxmc("单位类型名称");
        dwxx.setDwmc("单位名称");
        aidlResult.setCode(0);
        aidlResult.setMsg(GsonUtils.INSTANCE.toJson(dwxx));
        return GsonUtils.INSTANCE.toJson(aidlResult);
    }


    @Override
    public String getHtXmxx()  {
        AidlResult aidlResult = new AidlResult();
        HtXmxx htXmxx = new HtXmxx();
        htXmxx.setHtid("合同id");
        htXmxx.setHtmc("合同名称");
        htXmxx.setXmbh("项目编号");
        htXmxx.setXmmc("项目名称");
        aidlResult.setCode(0);
        aidlResult.setMsg(GsonUtils.INSTANCE.toJson(htXmxx));
        return GsonUtils.INSTANCE.toJson(aidlResult);
    }

    @Override
    public String getRyxx()  {
        AidlResult aidlResult = new AidlResult();
        Ryxx ryxx = new Ryxx();
        ryxx.setSfz("身份证");
        ryxx.setXm("人员名称");
        aidlResult.setCode(0);
        aidlResult.setMsg(GsonUtils.INSTANCE.toJson(ryxx));
        return GsonUtils.INSTANCE.toJson(aidlResult);
    }

    @Override
    public String getJWD()  {
        AidlResult aidlResult = new AidlResult();
        Jwdxx jwdxx = new Jwdxx();
        jwdxx.setJd("经度");
        jwdxx.setWd("纬度");
        aidlResult.setCode(0);
        aidlResult.setMsg(GsonUtils.INSTANCE.toJson(jwdxx));
        return GsonUtils.INSTANCE.toJson(aidlResult);
    }

    @Override
    public void setUID(String uid)  {
        Log.i(TAG, "setUID: " + uid);
    }

    @Override
    public void setFBH(String fbh)  {
        Log.i(TAG, "setFBH: " + fbh);
    }

    @Override
    public void setQBRZ(String uid) {
        Log.i(TAG, "setQBRZ: " + uid);
    }
}