package common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    public String addEntity(Entity entity) {
        String id = entity.getID();
        entityMap.put(id, entity);
        return id;
    }

    public void removeEntity(String id) {
        entityMap.remove(id);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public Entity getEntity(String id) {
        return entityMap.get(id);
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity entity : entityMap.values()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.isInstance(entity)) {
                    r.add(entity);
                }
            }
        }
        return r;
    }

}
