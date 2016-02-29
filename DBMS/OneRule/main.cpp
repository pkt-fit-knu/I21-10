#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#include <algorithm>
#include <queue>
#include <cmath>

#include <map>

using namespace std;
double avb, avc, avd, ave;
class DataEntry
{
    public:
    double par[4];
    char name[20];
    DataEntry(double a, double b, double c, double d, char e[])
    {
        par[0]=a;
        par[1]=b;
        par[2]=c;
        par[3]=d;

        int len=strlen(e);
        for(int i=0;i<=len;++i)
        {
            name[i]=e[i];
        }
    }
    DataEntry()
    {
        par[0]=0;
        par[1]=0;
        par[2]=0;
        par[3]=0;


    }
};
void toSpace(char a[])
{
    int len=strlen(a);
    for(int i=0;i<len;++i)
    {
        if(a[i]==',') a[i]=' ';

    }
}
 int rulemist[4];
 bool hasentry[100];
    int rulebest[4][100];
    bool merg[4][100];
bool areEqual(char b[], char c[])
{
    int blen=strlen(b);
    int clen=strlen(c);
    if(blen!=clen) return false;
    for(int i=0;i<blen;++i)
    {

        if(b[i]!=c[i]) return false;
    }
    return true;
}

int main()
{

    DataEntry isis[160];
    freopen("C:\\Users\\Grole\\Desktop\\iris\\iris.data","r",stdin);

    int mis=0;
    double b, c, d, e, f;


    for(int i=0;i<150;++i)
    {
        char a[20];
        double col=0, ver=0;
        //scanf("%s\n", a);
        scanf("%lf,%lf,%lf,%lf,%s\n",&b,&c,&d,&e,a);
        isis[i].par[0]=b;
        isis[i].par[1]=c;
        isis[i].par[2]=d;
        isis[i].par[3]=e;
        int len=strlen(a);
        for(int k=0;k<=len;++k)
        {
            isis[i].name[k]=a[k];
        }


    }
    int hits[100][3];


    for(int w=0;w<4;++w)
    {
        for(int i=0;i<100;++i)
        {
            for(int k=0;k<3;++k)
            hits[i][k]=0;
        }
        for(int i=0;i<150;++i)
        {
            hits[(int)(isis[i].par[w]*3)][i/50]++;
            hasentry[(int)(isis[i].par[w]*3)]=true;


        }
        for(int i=0;i<100;++i)
        {
            {
                if(i>0)
                {
                    if(merg[w][i-1])
                    {
                        for(int u=0;u<3;++u)
                        {
                            hits[i][u]+=hits[i-1][u];
                        }
                    }
                }
                int eta=0;
                if(hits[i][1]>hits[i][eta]) eta=1;
                if(hits[i][2]>hits[i][eta]) eta=2;

                rulebest[w][i]=eta;
                if(hits[i][eta]<10) merg[w][i]=true;

                for(int u=0;u<3;++u)
                {
                    if(eta!=u && !merg[w][i]) rulemist[w]+=hits[i][u];
                }
            }
        }
        printf("mistakes of rule %d = %d\n",w,rulemist[w]);

    }
    int bestrule=0;
    for(int i=0;i<4;++i)
        if(rulemist[i]<rulemist[bestrule]) bestrule=i;

    if(bestrule==0) printf("Best parameter is sepal length\n");
    else if(bestrule==1) printf("Best parameter is sepal width\n");
    else if(bestrule==2) printf("Best parameter is petal length\n");
    else if(bestrule==3) printf("Best parameter is petal width\n");
    printf("Codes:\n 0 - Iris-setosa\n 1 - Iris-versicolor\n 2 - Iris-virginica\n\n");
    printf("Describing best rule: \n");
    for(int i=0;i<100;++i)
    {
        if(hasentry[i])
        {
            printf("Range %.2lf",(double)i/3);
            while(merg[bestrule][i] && i<99) ++i;
            printf("-%.2lf - %d\n", (double)(i+1)/3-0.01,rulebest[bestrule][i]);

        }

    }


/*
    for(int i=0;i<50;++i)
    {
        //scanf("%s\n", a);
        scanf("%lf,%lf,%lf,%lf,%s\n",&b,&c,&d,&e,a);
        avb+=b;
        avc+=c;
        avd+=d;
        ave+=e;

    }
    printf("%.1lf %.1lf %.1lf %.1lf\n", avb/50, avc/50, avd/50,ave/50);
    avb=0;
    avc=0;
    avd=0;
    ave=0;
    for(int i=0;i<50;++i)
    {
        //scanf("%s\n", a);
        scanf("%lf,%lf,%lf,%lf,%s\n",&b,&c,&d,&e,a);
        avb+=b;
        avc+=c;
        avd+=d;
        ave+=e;
    }
    printf("%.1lf %.1lf %.1lf %.1lf\n", avb/50, avc/50, avd/50,ave/50);
    avb=0;
    avc=0;
    avd=0;
    ave=0;
    for(int i=0;i<50;++i)
    {
        //scanf("%s\n", a);
        scanf("%lf,%lf,%lf,%lf,%s\n",&b,&c,&d,&e,a);
        avb+=b;
        avc+=c;
        avd+=d;
        ave+=e;
    }
    printf("%.1lf %.1lf %.1lf %.1lf\n", avb/50, avc/50, avd/50,ave/50);
    avb=0;
    avc=0;
    avd=0;
    ave=0;*/


    return 0;
}
/*
5.9 2.8 4.3 1.3
6.6 3.0 5.6 2.0
*/
