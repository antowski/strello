
/* instead of core-config 
 * with <context:component-scan base-package="com.mkyong.helloworld.service" />*/

package strello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan({"strello"})
public class SpringRootConfig {
}
