package com.rae.creatingspace.server.contraption.movementbehaviour;

import com.rae.creatingspace.server.particle.RocketPlumeParticleData;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EngineMovementBehaviour implements MovementBehaviour {

    static RandomSource r = RandomSource.create();

    @Override
    public boolean isActive(MovementContext context) {
        return MovementBehaviour.super.isActive(context); //&& (context.contraption instanceof RocketContraption);
    }

    @Override
    public void tick(MovementContext context) {
        if (context.world == null || !context.world.isClientSide || context.position == null
                || context.disabled)
            return;

        Level world = context.world;
        Vec3 pos = context.position;
        ParticleOptions particle = new RocketPlumeParticleData(0.1f);
        spawnParticles(world,pos.add(0,-1.5,0),Direction.DOWN,10,particle,3f,0.2f);

    }

    //copied from Create's FluidFX
    public static void spawnParticles(Level world, Vec3 pos, Direction side, int amount, ParticleOptions particle,
                                      float angleDegree,float radius) {
        Vec3 directionVec = Vec3.atLowerCornerOf(side.getNormal());

        //make a
        /*for (float tetha = 0; tetha < Math.PI*2; tetha += 0.1F) {

        }*/
        for (int i = 0; i < amount; i++) {
            Vec3 vec = VecHelper.offsetRandomly(Vec3.ZERO, r, radius)
                    .normalize();
            Vec3 posVec = VecHelper.clampComponentWise(vec,radius);
            //posVec = posVec.multiply(1,0,1);
            Vec3 motion = vec.scale(Math.asin(angleDegree*Math.PI/180)).add(directionVec.scale(Math.acos(angleDegree*Math.PI/180)));
            posVec = posVec.add(pos);
            world.addAlwaysVisibleParticle(particle, posVec.x, posVec.y, posVec.z, motion.x, motion.y, motion.z);
        }
    }
}
