package cn.race.gateway.component;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    private final RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher publisher;

    public DynamicRouteServiceImpl(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    /**
     * 添加路由
     *
     * @param definition
     * @return
     */
    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent((new RefreshRoutesEvent(definition)));
        return "success";
    }

    /**
     * 更新路由
     *
     * @param definition
     * @return
     */
    public String update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route,routeId:" + definition.getId();
        }
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent((definition)));
        return "success";
    }

    /**
     * 批量更新路由
     *
     * @param list
     * @return
     */
    public String updateBatch(List<RouteDefinition> list) {
        if (CollectionUtils.isEmpty(list)) {
            return "success";
        }
        list.forEach(definition -> {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        });
        list.forEach(definition -> {
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        });
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /**
     * 删除路由
     *
     * @param id
     * @return
     */
    public String delete(String id) {
        this.routeDefinitionWriter.delete(Mono.just(id));
        return "delete success";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
