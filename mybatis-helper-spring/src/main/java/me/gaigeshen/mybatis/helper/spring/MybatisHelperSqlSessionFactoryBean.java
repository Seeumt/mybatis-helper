package me.gaigeshen.mybatis.helper.spring;

import me.gaigeshen.mybatis.helper.MybatisHelperConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContextException;

/**
 * Using this class instead, enable mybatis helper
 * This class is a {@link SqlSessionFactoryBean}
 *
 * @author gaigeshen
 * @see SqlSessionFactoryBean
 */
public class MybatisHelperSqlSessionFactoryBean extends SqlSessionFactoryBean implements BeanPostProcessor {

  private static final Logger log = LoggerFactory.getLogger(MybatisHelperSqlSessionFactoryBean.class);

  /**
   * Create {@link MybatisHelperSqlSessionFactoryBean}
   */
  public MybatisHelperSqlSessionFactoryBean() {
    // Configure when create this object
    log.info("Configure mybatis helper");
    MybatisHelperConfigurer.create().configure();
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    // We invoke initialize result mappings method
    // after every mapper factory bean initialization
    if (bean instanceof MapperFactoryBean) {
      log.debug("Initialize result mappings of MapperFactoryBean: {}", beanName);
      try {
        MybatisHelperConfigurer.create().initializeResultMappings(
                ((MapperFactoryBean<?>) bean).getSqlSessionFactory().getConfiguration());
      } catch (Exception e) {
        throw new ApplicationContextException("Could not initialize result mappings", e);
      }
    }
    return bean;
  }
}
