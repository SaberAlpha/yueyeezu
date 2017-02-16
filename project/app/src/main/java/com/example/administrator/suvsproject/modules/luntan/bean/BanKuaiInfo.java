package com.example.administrator.suvsproject.modules.luntan.bean;

import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
@Table(name = "BanKuaiInfo")
public class BanKuaiInfo {

    /**
     * gid : 188
     * name : ｅ族大队
     * sub : [{"fid":10,"name":"粤","sub":[{"fid":116,"name":"深圳中队","isfavorite":0}],"isfavorite":0},{"fid":12,"name":"京","sub":[{"fid":769,"name":"昌平分队","isfavorite":0},{"fid":780,"name":"雪豹分队","isfavorite":0},{"fid":779,"name":"机场分队","isfavorite":0},{"fid":777,"name":"野MEN分队","isfavorite":0},{"fid":776,"name":"自由军团分队","isfavorite":0},{"fid":809,"name":"房山分队","isfavorite":0},{"fid":810,"name":"动能平方分队","isfavorite":0},{"fid":811,"name":"兴宏越野分队","isfavorite":0},{"fid":812,"name":"北京猿人越野分队","isfavorite":0},{"fid":841,"name":"菱豹兵团分队","isfavorite":0},{"fid":842,"name":"霸巡分队","isfavorite":0}],"isfavorite":0},{"fid":31,"name":"渝","sub":[{"fid":553,"name":"渝东分队","isfavorite":0},{"fid":2040,"name":"渝西分队","isfavorite":0}],"isfavorite":0},{"fid":64,"name":"川","sub":[{"fid":422,"name":"攀枝花分队","isfavorite":0},{"fid":578,"name":"凉山分队","isfavorite":0},{"fid":640,"name":"南充分队","isfavorite":0},{"fid":703,"name":"绵阳分队","isfavorite":0},{"fid":762,"name":"巴中分队","isfavorite":0},{"fid":813,"name":"自贡分队","isfavorite":0}],"isfavorite":0},{"fid":35,"name":"鲁","sub":[{"fid":82,"name":"东营分队","isfavorite":0},{"fid":83,"name":"济宁分队","isfavorite":0},{"fid":80,"name":"青岛分队","isfavorite":0},{"fid":81,"name":"济南分队","isfavorite":0},{"fid":90,"name":"烟台分队","isfavorite":0},{"fid":265,"name":"威海分队","isfavorite":0},{"fid":91,"name":"泰山分队","isfavorite":0},{"fid":151,"name":"潍坊分队","isfavorite":0},{"fid":152,"name":"日照分队","isfavorite":0},{"fid":153,"name":"临沂分队","isfavorite":0},{"fid":184,"name":"淄博分队","isfavorite":0},{"fid":185,"name":"聊城分队","isfavorite":0},{"fid":186,"name":"枣庄分队","isfavorite":0},{"fid":204,"name":"滨州分队","isfavorite":0},{"fid":227,"name":"德州分队","isfavorite":0},{"fid":324,"name":"菏泽分队","isfavorite":0},{"fid":383,"name":"莱芜分队","isfavorite":0}],"isfavorite":0},{"fid":37,"name":"沪","sub":[{"fid":344,"name":"上海FB运动营","isfavorite":0},{"fid":429,"name":"上海FB越野营","isfavorite":0}],"isfavorite":0},{"fid":39,"name":"豫","isfavorite":0},{"fid":40,"name":"浙","sub":[{"fid":174,"name":"温州分队","isfavorite":0},{"fid":218,"name":"台州分队","isfavorite":0},{"fid":304,"name":"宁波分队","isfavorite":0},{"fid":369,"name":"金华分队","isfavorite":0},{"fid":517,"name":"绍兴分队","isfavorite":0},{"fid":675,"name":"杭州分队","isfavorite":0},{"fid":720,"name":"嘉兴分队","isfavorite":0}],"isfavorite":0},{"fid":42,"name":"辽","sub":[{"fid":131,"name":"辽西中队","isfavorite":0},{"fid":246,"name":"辽北中队","isfavorite":0},{"fid":360,"name":"辽东中队","isfavorite":0},{"fid":361,"name":"辽南中队","isfavorite":0},{"fid":362,"name":"沈阳分队","isfavorite":0},{"fid":141,"name":"大连分队","isfavorite":0}],"isfavorite":0},{"fid":45,"name":"赣","sub":[{"fid":121,"name":"赣西中队","isfavorite":0},{"fid":124,"name":"赣南中队","isfavorite":0},{"fid":276,"name":"赣东北中队","isfavorite":0}],"isfavorite":0},{"fid":51,"name":"津","isfavorite":0},{"fid":52,"name":"苏","sub":[{"fid":175,"name":"苏州分队","isfavorite":0},{"fid":451,"name":"徐州分队","isfavorite":0},{"fid":807,"name":"淮安分队","isfavorite":0},{"fid":817,"name":"宿迁分队","isfavorite":0},{"fid":834,"name":"常州分队","isfavorite":0},{"fid":845,"name":"无锡分队","isfavorite":0},{"fid":846,"name":"南通分队","isfavorite":0}],"isfavorite":0},{"fid":53,"name":"陕","sub":[{"fid":172,"name":"咸阳分队","isfavorite":0},{"fid":173,"name":"安康分队","isfavorite":0},{"fid":225,"name":"陕北中队","isfavorite":0},{"fid":249,"name":"渭南分队","isfavorite":0},{"fid":250,"name":"渭北分队","isfavorite":0},{"fid":268,"name":"汉中分队","isfavorite":0},{"fid":355,"name":"宝鸡分队","isfavorite":0},{"fid":556,"name":"延安分队","isfavorite":0},{"fid":802,"name":"商洛分队","isfavorite":0},{"fid":803,"name":"铜川分队","isfavorite":0},{"fid":808,"name":"杨凌分队","isfavorite":0}],"isfavorite":0},{"fid":54,"name":"滇","sub":[{"fid":491,"name":"曲靖分队","isfavorite":0},{"fid":766,"name":"昆明分队","isfavorite":0},{"fid":767,"name":"玉溪分队","isfavorite":0},{"fid":768,"name":"文山分队","isfavorite":0},{"fid":791,"name":"保山分队","isfavorite":0},{"fid":819,"name":"红河分队","isfavorite":0},{"fid":771,"name":"滇大版务区","isfavorite":0}],"isfavorite":0},{"fid":55,"name":"蒙","sub":[{"fid":354,"name":"阿拉善分队","isfavorite":0},{"fid":111,"name":"包头中队","isfavorite":0},{"fid":264,"name":"鄂尔多斯分队","isfavorite":0},{"fid":89,"name":"通辽分队","isfavorite":0},{"fid":61,"name":"呼伦贝尔中队","isfavorite":0},{"fid":364,"name":"锡林郭勒分队","isfavorite":0},{"fid":457,"name":"赤峰分队","isfavorite":0},{"fid":456,"name":"巴彦淖尔分队","isfavorite":0},{"fid":459,"name":"乌海分队","isfavorite":0},{"fid":523,"name":"呼和浩特中队","isfavorite":0},{"fid":591,"name":"兴安盟分队","isfavorite":0},{"fid":704,"name":"二连浩特分队","isfavorite":0}],"isfavorite":0},{"fid":57,"name":"湘","sub":[{"fid":460,"name":"长沙分队","isfavorite":0},{"fid":594,"name":"怀化分队","isfavorite":0},{"fid":595,"name":"永州分队","isfavorite":0},{"fid":596,"name":"湘西州分队","isfavorite":0},{"fid":597,"name":"娄底分队","isfavorite":0},{"fid":600,"name":"张家界分队","isfavorite":0},{"fid":601,"name":"衡阳分队","isfavorite":0},{"fid":602,"name":"郴州分队","isfavorite":0},{"fid":603,"name":"邵阳分队","isfavorite":0},{"fid":604,"name":"湘潭分队","isfavorite":0},{"fid":485,"name":"株洲分队","isfavorite":0},{"fid":605,"name":"常德分队","isfavorite":0},{"fid":606,"name":"岳阳分队","isfavorite":0},{"fid":488,"name":"益阳分队","isfavorite":0},{"fid":607,"name":"湘大版务区","isfavorite":0}],"isfavorite":0},{"fid":59,"name":"桂","sub":[{"fid":307,"name":"桂林分队","isfavorite":0},{"fid":310,"name":"柳州分队","isfavorite":0},{"fid":311,"name":"玉林分队","isfavorite":0},{"fid":309,"name":"南宁分队","isfavorite":0},{"fid":312,"name":"钦北防中队","isfavorite":0},{"fid":544,"name":"桂东分队","isfavorite":0},{"fid":698,"name":"贵港分队","isfavorite":0}],"isfavorite":0},{"fid":60,"name":"鄂","isfavorite":0},{"fid":65,"name":"黔","isfavorite":0},{"fid":66,"name":"黑","isfavorite":0},{"fid":67,"name":"吉","isfavorite":0},{"fid":69,"name":"晋","sub":[{"fid":182,"name":"运城分队","isfavorite":0},{"fid":202,"name":"太原分队","isfavorite":0},{"fid":205,"name":"大同分队","isfavorite":0},{"fid":206,"name":"朔州分队","isfavorite":0},{"fid":215,"name":"阳泉分队","isfavorite":0},{"fid":219,"name":"忻州分队","isfavorite":0},{"fid":277,"name":"晋城分队","isfavorite":0},{"fid":278,"name":"长治分队","isfavorite":0},{"fid":292,"name":"吕梁分队","isfavorite":0},{"fid":293,"name":"晋中分队","isfavorite":0},{"fid":376,"name":"临汾分队","isfavorite":0},{"fid":685,"name":"晋大版务区","isfavorite":0}],"isfavorite":0},{"fid":70,"name":"冀","sub":[{"fid":183,"name":"廊坊分队","isfavorite":0},{"fid":241,"name":"石家庄分队","isfavorite":0},{"fid":273,"name":"唐山分队","isfavorite":0},{"fid":274,"name":"秦皇岛分队","isfavorite":0},{"fid":329,"name":"张家口分队","isfavorite":0},{"fid":330,"name":"承德分队","isfavorite":0},{"fid":331,"name":"邯郸分队","isfavorite":0},{"fid":332,"name":"衡水分队","isfavorite":0},{"fid":333,"name":"邢台分队","isfavorite":0},{"fid":334,"name":"沧州分队","isfavorite":0},{"fid":335,"name":"保定分队","isfavorite":0}],"isfavorite":0},{"fid":71,"name":"闽","sub":[{"fid":509,"name":"闽南中队","isfavorite":0}],"isfavorite":0},{"fid":86,"name":"皖","isfavorite":0},{"fid":93,"name":"甘","sub":[{"fid":458,"name":"庆阳分队","isfavorite":0}],"isfavorite":0},{"fid":101,"name":"新","sub":[{"fid":614,"name":"北疆分队","isfavorite":0},{"fid":615,"name":"南疆分队","isfavorite":0}],"isfavorite":0},{"fid":117,"name":"宁","isfavorite":0},{"fid":120,"name":"青","isfavorite":0},{"fid":137,"name":"港","isfavorite":0},{"fid":144,"name":"琼","sub":[{"fid":830,"name":"海口中队","isfavorite":0},{"fid":831,"name":"三亚中队","isfavorite":0}],"isfavorite":0},{"fid":138,"name":"澳","isfavorite":0},{"fid":145,"name":"藏","isfavorite":0},{"fid":146,"name":"台","isfavorite":0},{"fid":445,"name":"北美纵队","sub":[{"fid":585,"name":"美国","isfavorite":0},{"fid":586,"name":"加拿大","isfavorite":0}],"isfavorite":0},{"fid":446,"name":"澳新纵队","isfavorite":0},{"fid":447,"name":"非洲纵队","isfavorite":0},{"fid":452,"name":"欧洲纵队","isfavorite":0}]
     */

    private List<BbsinfoBean> bbsinfo;

    public List<BbsinfoBean> getBbsinfo() {
        return bbsinfo;
    }

    public void setBbsinfo(List<BbsinfoBean> bbsinfo) {
        this.bbsinfo = bbsinfo;
    }

    public static class BbsinfoBean {
        private int gid;
        private String name;
        private List<BanKuaiSonInfo> sList;

        public List<BanKuaiSonInfo> getsList() {
            return sList;
        }

        public void setsList(List<BanKuaiSonInfo> sList) {
            this.sList = sList;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
