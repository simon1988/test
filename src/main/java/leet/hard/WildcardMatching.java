package leet.hard;
/**
 * 
 * @author Simon
 *
 *
 * bool isMatch(const char *s, const char *p)
    {
        const char* star=NULL;
        const char* ss=s;
        while(*s)
        {
            if(*p=='*')
            {
                star=p;++p;ss=s;    
            }
            else if(*p=='?' || *p==*s)
            {
                ++p;++s;
            }
            else if(NULL!=star)
            {
                p=star+1;s=++ss;    
            }
            else
                return false;
        }
        while(*p=='*')
            ++p;
        return !*p;
        
    }
 */
public class WildcardMatching {

}
