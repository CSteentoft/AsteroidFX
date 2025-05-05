import common.services.IPostEntityProcessingService;

module Collisions {
    requires Common;

    provides IPostEntityProcessingService with collisions.CollisionDetector;
}